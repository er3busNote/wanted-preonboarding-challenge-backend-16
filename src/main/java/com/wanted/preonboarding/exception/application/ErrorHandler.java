package com.wanted.preonboarding.exception.application;

import com.wanted.preonboarding.exception.dto.ErrorCode;
import com.wanted.preonboarding.exception.dto.ErrorResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErrorHandler {

    public ErrorResponse buildError(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .code(errorCode.getCode())
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .build();
    }

    public ErrorResponse buildError(ErrorCode errorCode, List<ErrorResponse.ValidationError> validationErrorList) {
        return ErrorResponse.builder()
                .code(errorCode.getCode())
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .errors(validationErrorList)
                .build();
    }
}
