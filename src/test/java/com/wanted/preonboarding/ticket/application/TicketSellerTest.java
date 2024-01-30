package com.wanted.preonboarding.ticket.application;

import com.wanted.preonboarding.ticket.domain.dto.PerformanceInfo;
import com.wanted.preonboarding.ticket.domain.dto.ReserveInfo;
import com.wanted.preonboarding.ticket.infrastructure.repository.PerformanceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TicketSellerTest {
    @Autowired
    private TicketSeller ticketSeller;
    @Autowired
    private PerformanceRepository performanceRepository;

    @Test
    @DisplayName("예매 가능한 공연 및 전시정보 확인")
    public void getAllPerformanceInfoList() {
        System.out.println("RESULT => " + ticketSeller.getAllPerformanceInfoList());
    }

    @Test
    @DisplayName("예매 가능한 공연 및 전시 상세정보 확인")
    public void getPerformanceInfoDetail() {
        System.out.println("RESULT => " + ticketSeller.getPerformanceInfoDetail("레베카"));
    }

    @Test
    @DisplayName("예매하기")
    public void reserve() {
        PerformanceInfo performanceInfo = ticketSeller.getPerformanceInfoDetail("레베카");
        boolean result = ticketSeller.reserve(ReserveInfo.builder()
                .performanceId(performanceInfo.getPerformanceId())
                .reservationName("유진호")
                .reservationPhoneNumber("010-1234-1234")
                .reservationStatus(performanceInfo.getIsReserve())
                .amount(200000)
                .round(1)
                .line('A')
                .seat(1)
                .build()
        );
        assertTrue(result);
    }
}
