package com.wanted.preonboarding.ticket.domain.dto;

import com.wanted.preonboarding.ticket.domain.entity.Reservation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ReserveInfo {
    // 공연 및 전시 정보 + 예약자 정보
    @NotNull
    private UUID performanceId; // 공연 ID
    private String performanceName; // 공연명
    @NotBlank
    private String reservationName; // 예매자 이름
    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다")
    private String reservationPhoneNumber; // 예매자 전화번호
    @NotBlank
    private String reservationStatus; // 예약; 취소;
    @Positive
    private long amount;
    @Positive
    private int round; // 회차
    private char line;
    @Positive
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

    public void setAmount(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(Long.toString(amount));
        }
        this.amount = amount;
    }
}
