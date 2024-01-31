package com.wanted.preonboarding.ticket.presentation;

import com.wanted.preonboarding.core.domain.response.ResponseHandler;
import com.wanted.preonboarding.ticket.application.TicketSeller;
import com.wanted.preonboarding.discount.domain.dto.CustomerInfo;
import com.wanted.preonboarding.ticket.domain.dto.ReserveInfo;
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

    @GetMapping("/detail")
    public ResponseEntity<ResponseHandler<List<ReserveInfo>>> getReserveInfoDetail(@Valid CustomerInfo customerInfo) {
        System.out.println("getReserveInfoDetail");
        return ResponseEntity
                .ok()
                .body(ResponseHandler.<List<ReserveInfo>>builder()
                        .message("Success")
                        .statusCode(HttpStatus.OK)
                        .data(ticketSeller.getReserveInfoDetail(customerInfo))
                        .build()
                );
    }

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
