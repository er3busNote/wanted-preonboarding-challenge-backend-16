package com.wanted.preonboarding.ticket.application;

import com.wanted.preonboarding.discount.infrastructure.repository.CustomerRepository;
import com.wanted.preonboarding.ticket.domain.dto.ReserveInfo;
import com.wanted.preonboarding.ticket.domain.entity.Reservation;
import com.wanted.preonboarding.ticket.infrastructure.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TicketCancel {
    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public boolean reserveCancel(Integer id) {
        log.info("reservation ID => {}", id);
        Optional<Reservation> info = reservationRepository.findById(id);
        if (info.isPresent()) {
            reservationRepository.deleteById(id);
            Reservation reservation = info.get();
            Long count = reservationRepository.countByNameAndPhoneNumber(reservation);
            log.info("reservation COUNT => {}", count);
            if (count == 0) {
                customerRepository.deleteByNameAndPhoneNumber(reservation);
            }
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public boolean reserveCancel(ReserveInfo reserveInfo) {
        log.info("reserveInfo ID => {}", reserveInfo.getPerformanceId());
        Optional<Reservation> info = reservationRepository.findById(Reservation.of(reserveInfo));
        if (info.isPresent()) {
            Reservation reservation = info.get();
            log.info("reservation ID => {}", reservation.getId());
            reservationRepository.delete(reservation);
            return true;
        } else {
            return false;
        }
    }
}
