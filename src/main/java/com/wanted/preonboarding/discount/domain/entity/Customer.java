package com.wanted.preonboarding.discount.domain.entity;

import com.wanted.preonboarding.discount.domain.dto.DiscountType;
import com.wanted.preonboarding.ticket.domain.dto.ReserveInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, name = "phone_number")
    private String phoneNumber;
    @Column(nullable = false)
    private DiscountType type;

    public static Customer of(ReserveInfo reserveInfo) {
        return Customer.builder()
                .name(reserveInfo.getReservationName())
                .phoneNumber(reserveInfo.getReservationPhoneNumber())
                .type(DiscountType.VIP)
                .build();
    }
}
