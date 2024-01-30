package com.wanted.preonboarding.ticket.domain.dto;

import com.wanted.preonboarding.ticket.domain.entity.Performance;
import com.wanted.preonboarding.ticket.domain.entity.Reservation;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class ReserveInfo {
    // 공연 및 전시 정보 + 예약자 정보
    private UUID performanceId; // 공연 ID
    private String performanceName; // 공연명
    private String reservationName; // 예매자 이름
    private String reservationPhoneNumber; // 예매자 전화번호
    private String reservationStatus; // 예약; 취소;
    private long amount;
    private int round; // 회차
    private char line;
    private int seat; // 좌석정보

    public static ReserveInfo of(Reservation entity) {
        return ReserveInfo.builder()
                .performanceId(entity.getPerformanceId().getId())
                .performanceName(entity.getPerformanceId().getName())
                .reservationName(entity.getName())
                .reservationPhoneNumber(entity.getPhoneNumber())
                .reservationStatus(entity.getPerformanceId().getIsReserve())
                .amount(entity.getPerformanceId().getPrice())
                .round(entity.getRound())
                .line(entity.getLine())
                .seat(entity.getSeat())
                .build();
    }
}
