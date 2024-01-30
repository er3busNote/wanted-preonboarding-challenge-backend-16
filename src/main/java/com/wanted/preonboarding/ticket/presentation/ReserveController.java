package com.wanted.preonboarding.ticket.presentation;

import com.wanted.preonboarding.core.domain.response.ResponseHandler;
import com.wanted.preonboarding.ticket.application.TicketSeller;
import com.wanted.preonboarding.ticket.domain.dto.ReserveInfo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserve")
@RequiredArgsConstructor
public class ReserveController {
    private final TicketSeller ticketSeller;

    @PostMapping("/")
    public ResponseEntity<ResponseHandler<ReserveInfo>> reservation(@RequestBody @Valid ReserveInfo reserveInfo) {
        System.out.println("reservation");
        ticketSeller.reserve(reserveInfo);  // 결제 이후, 잔액 반영...!
        return ResponseEntity
                .ok()
                .body(ResponseHandler.<ReserveInfo>builder()
                        .message("Success")
                        .statusCode(HttpStatus.OK)
                        .data(reserveInfo)
                        .build()
                );
    }
}
