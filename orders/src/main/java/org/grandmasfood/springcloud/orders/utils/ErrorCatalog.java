package org.grandmasfood.springcloud.orders.utils;

import lombok.Getter;

@Getter
public enum ErrorCatalog {

    ORDER_NOT_FOUND("ORDER_NOT_FOUND", "Order not found"),

    INVALID_ORDER("INVALID_ORDER", "Invalid order"),

    OVERFLOW_ORDER_DATA("OVERFLOW_ORDER_DATA", "Overflow data"),

    GENERIC_ERROR("GENERIC_ERROR", "An error occurred");


    private final String code;
    private final String message;

    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
