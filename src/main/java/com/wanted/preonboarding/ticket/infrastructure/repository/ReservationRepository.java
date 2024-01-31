package com.wanted.preonboarding.ticket.infrastructure.repository;

import com.wanted.preonboarding.ticket.domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query("SELECT r FROM Reservation r JOIN FETCH r.performanceId WHERE r.name = :name AND r.phoneNumber = :phoneNumber")
    List<Reservation> findByNameAndPhoneNumber(@Param("name") String name, @Param("phoneNumber") String phoneNumber);
}
