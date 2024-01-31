package com.wanted.preonboarding.discount.infrastructure.repository;

import com.wanted.preonboarding.discount.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByNameAndPhoneNumber(String name, String phoneNumber);
}
