package com.wanted.preonboarding.discount.application;

import com.wanted.preonboarding.discount.domain.dto.DiscountType;
import com.wanted.preonboarding.discount.domain.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000;   // 1000원 할인

    @Override
    public int discount(Customer customer, int price) {
        if (customer.getType() == DiscountType.VIP)
            return price - discountFixAmount;
        else return price;
    }
}
