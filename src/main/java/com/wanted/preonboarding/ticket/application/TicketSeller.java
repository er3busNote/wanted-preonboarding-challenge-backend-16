package com.wanted.preonboarding.ticket.application;

import com.wanted.preonboarding.ticket.domain.dto.CustomerInfo;
import com.wanted.preonboarding.ticket.domain.dto.PerformanceInfo;
import com.wanted.preonboarding.ticket.domain.dto.ReserveInfo;
import com.wanted.preonboarding.ticket.domain.entity.Performance;
import com.wanted.preonboarding.ticket.domain.entity.Reservation;
import com.wanted.preonboarding.ticket.infrastructure.repository.PerformanceRepository;
import com.wanted.preonboarding.ticket.infrastructure.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TicketSeller {
    private final PerformanceRepository performanceRepository;
    private final ReservationRepository reservationRepository;
    private long totalAmount = 0L;

    public List<PerformanceInfo> getAllPerformanceInfoList() {
        return performanceRepository.findByIsReserve("enable")
            .stream()
            .map(PerformanceInfo::of)
            .toList();
    }

    public PerformanceInfo getPerformanceInfoDetail(String uuid) {
        return PerformanceInfo.of(performanceRepository.findById(UUID.fromString(uuid))
                .orElseThrow(EntityNotFoundException::new));
    }

    public List<ReserveInfo> getReserveInfoDetail(CustomerInfo customerInfo) {
        String name = customerInfo.getName();
        String phoneNumber = customerInfo.getPhoneNumber();
        List<Reservation> reservations = reservationRepository.findByNameAndPhoneNumber(name, phoneNumber);
        return reservations.stream().map(ReserveInfo::of).collect(Collectors.toList());
    }

    public boolean reserve(ReserveInfo reserveInfo) {
        log.info("reserveInfo ID => {}", reserveInfo.getPerformanceId());
        Performance info = performanceRepository.findById(reserveInfo.getPerformanceId())
            .orElseThrow(EntityNotFoundException::new);
        String enableReserve = info.getIsReserve();
        if (enableReserve.equalsIgnoreCase("enable")) {
            // 1. 결제
            String name = info.getName();
            int price = info.getPrice();
            reserveInfo.setPerformanceName(name);
            reserveInfo.setAmount(reserveInfo.getAmount() - price);
            // 2. 예매 진행
            reservationRepository.save(Reservation.of(reserveInfo));
            return true;
        } else {
            return false;
        }
    }

}
