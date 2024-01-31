package com.wanted.preonboarding.discount.domain.dto;

public enum DiscountType {
    NONE(0),
    VIP(1);

    private final int category;

    DiscountType(int category) {
        this.category = category;
    }

    public int getCategory() {
        return category;
    }
}
