package com.wanted.preonboarding.ticket.application;

import com.wanted.preonboarding.discount.domain.dto.CustomerInfo;
import com.wanted.preonboarding.ticket.domain.dto.PerformanceInfo;
import com.wanted.preonboarding.ticket.domain.dto.ReserveInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TicketSellerTest {
    @Autowired
    private TicketSeller ticketSeller;
    @Autowired
    private TicketCancel ticketCancel;

    @Test
    @DisplayName("공연/전시 정보 목록 조회 성공")
    public void getAllPerformanceInfoList() {
        System.out.println("RESULT => " + ticketSeller.getAllPerformanceInfoList());
    }

    @Test
    @DisplayName("공연/전시 정보 상세 조회 성공")
    public void getPerformanceInfoDetail() {
        List<PerformanceInfo> performanceInfos = ticketSeller.getAllPerformanceInfoList();
        PerformanceInfo performanceInfo = performanceInfos.get(0);
        UUID uuid = performanceInfo.getPerformanceId();
        System.out.println("RESULT => " + ticketSeller.getPerformanceInfoDetail(uuid.toString()));
    }

    @Test
    @DisplayName("예약 조회 성공")
    public void getReserveInfoDetail() {
        CustomerInfo customerInfo = new CustomerInfo("유진호", "010-1234-1234");
        System.out.println("RESULT => " + ticketSeller.getReserveInfoDetail(customerInfo));
    }

    @Test
    @DisplayName("예약 취소 성공")
    public void reserveCancel() {
        List<PerformanceInfo> performanceInfos = ticketSeller.getAllPerformanceInfoList();
        PerformanceInfo performanceInfo = performanceInfos.get(0);
        boolean result = ticketCancel.reserveCancel(this.getReserveInfo(performanceInfo));
        assertTrue(result);
    }

    @Test
    @DisplayName("예약 성공")
    public void reserve() {
        List<PerformanceInfo> performanceInfos = ticketSeller.getAllPerformanceInfoList();
        PerformanceInfo performanceInfo = performanceInfos.get(0);
        boolean result = ticketSeller.reserve(this.getReserveInfo(performanceInfo));
        assertTrue(result);
    }

    private ReserveInfo getReserveInfo(PerformanceInfo performanceInfo) {
        return ReserveInfo.builder()
                .performanceId(performanceInfo.getPerformanceId())
                .reservationName("유진호")
                .reservationPhoneNumber("010-1234-1234")
                .reservationStatus(performanceInfo.getIsReserve())
                .amount(200000)
                .round(1)
                .line('A')
                .seat(1)
                .build();
    }
}
