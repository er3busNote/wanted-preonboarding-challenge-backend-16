package com.wanted.preonboarding.discount.application;

import com.wanted.preonboarding.discount.domain.entity.Customer;

public interface DiscountPolicy {

    int discount(Customer customer, int price);
}
