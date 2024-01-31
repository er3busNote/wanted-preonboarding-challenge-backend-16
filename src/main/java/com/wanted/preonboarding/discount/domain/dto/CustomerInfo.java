package com.wanted.preonboarding.discount.domain.dto;

import com.wanted.preonboarding.ticket.domain.dto.ReserveInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CustomerInfo {
    @NotBlank
    private String name; // 예매자 이름
    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다")
    private String phoneNumber; // 예매자 전화번호

    public static CustomerInfo of(ReserveInfo reserveInfo) {
        return CustomerInfo.builder()
                .name(reserveInfo.getReservationName())
                .phoneNumber(reserveInfo.getReservationPhoneNumber())
                .build();
    }
}
