package com.wanted.preonboarding.ticket.domain.entity;

import com.wanted.preonboarding.ticket.domain.dto.PerformanceInfo;
import com.wanted.preonboarding.ticket.domain.dto.ReserveInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Performance {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false)
    private int round;
    @Column(nullable = false)
    private int type;
    @Column(nullable = false)
    private Date start_date;
    @Column(nullable = false, name = "is_reserve", columnDefinition = "varchar default 'disable'")
    private String isReserve;
    @OneToMany(mappedBy = "performanceId")
    private List<Reservation> reservations;

    public static Performance of(ReserveInfo info) {
        return Performance.builder()
                .id(info.getPerformanceId())
                .name(info.getPerformanceName())
                .price(Long.valueOf(info.getAmount()).intValue())
                .round(info.getRound())
                .isReserve(info.getReservationStatus())
                .build();
    }
}
