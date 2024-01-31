package com.wanted.preonboarding.discount.infrastructure.repository;

import com.wanted.preonboarding.discount.domain.dto.CustomerInfo;
import com.wanted.preonboarding.discount.domain.entity.Customer;
import com.wanted.preonboarding.ticket.domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByNameAndPhoneNumber(String name, String phoneNumber);
    void deleteByNameAndPhoneNumber(String name, String phoneNumber);

    default Optional<Customer> findByNameAndPhoneNumber(CustomerInfo customerInfo) {
        String name = customerInfo.getName();
        String phoneNumber = customerInfo.getPhoneNumber();
        return this.findByNameAndPhoneNumber(name, phoneNumber);
    }

    default void deleteByNameAndPhoneNumber(Reservation reservation) {
        String name = reservation.getName();
        String phoneNumber = reservation.getPhoneNumber();
        deleteByNameAndPhoneNumber(name, phoneNumber);
    }
}
