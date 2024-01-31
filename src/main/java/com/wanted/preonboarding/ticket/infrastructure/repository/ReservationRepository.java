package com.wanted.preonboarding.ticket.infrastructure.repository;

import com.wanted.preonboarding.discount.domain.dto.CustomerInfo;
import com.wanted.preonboarding.ticket.domain.entity.Performance;
import com.wanted.preonboarding.ticket.domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query("SELECT r FROM Reservation r JOIN FETCH r.performanceId WHERE r.name = :name AND r.phoneNumber = :phoneNumber")
    List<Reservation> findByNameAndPhoneNumber(@Param("name") String name, @Param("phoneNumber") String phoneNumber);
    Optional<Reservation> findByPerformanceIdAndNameAndPhoneNumberAndRoundAndLineAndSeat(Performance performance, String name, String phoneNumber, int round, char line, int seat);
    Long countByNameAndPhoneNumber(String name, String phoneNumber);

    default List<Reservation> findByNameAndPhoneNumber(CustomerInfo customerInfo) {
        String name = customerInfo.getName();
        String phoneNumber = customerInfo.getPhoneNumber();
        return this.findByNameAndPhoneNumber(name, phoneNumber);
    }

    default Long countByNameAndPhoneNumber(Reservation reservation) {
        String name = reservation.getName();
        String phoneNumber = reservation.getPhoneNumber();
        return this.countByNameAndPhoneNumber(name, phoneNumber);
    }

    default Optional<Reservation> findById(Reservation reservation) {
        Performance performance = reservation.getPerformanceId();
        String name = reservation.getName();
        String phoneNumber = reservation.getPhoneNumber();
        int round = reservation.getRound();
        char line = reservation.getLine();
        int seat = reservation.getSeat();
        return this.findByPerformanceIdAndNameAndPhoneNumberAndRoundAndLineAndSeat(performance, name, phoneNumber, round, line, seat);
    }
}
