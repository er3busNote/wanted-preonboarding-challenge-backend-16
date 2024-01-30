package com.wanted.preonboarding.ticket.exception.presentation;

import com.wanted.preonboarding.ticket.exception.dto.ErrorCode;
import com.wanted.preonboarding.ticket.exception.dto.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ErrorExceptionHandler extends ResponseEntityExceptionHandler {

    private final ErrorHandler errorHandler;

//    @ExceptionHandler(BindException.class)
//    public ResponseEntity<ErrorResponse> handleBindException(BindException e) {
//        ErrorCode errorCode = ErrorCode.INVALID_PARAMETER;
//        List<ErrorResponse.ValidationError> validationErrorList = e.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(ErrorResponse.ValidationError::of)
//                .collect(Collectors.toList());
//        return ResponseEntity.status(errorCode.getStatus())
//                .body(errorHandler.buildError(errorCode, validationErrorList));
//    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        ErrorCode errorCode = ErrorCode.DISPLAY_NOT_FOUND;
        return ResponseEntity.status(errorCode.getStatus())
                .body(errorHandler.buildError(errorCode));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(DataIntegrityViolationException e) {
        ErrorCode errorCode = ErrorCode.SEATS_SOLD_OUT;
        return ResponseEntity.status(errorCode.getStatus())
                .body(errorHandler.buildError(errorCode));
    }
}
