package org.grandmasfood.springcloud.clients.utils;

import lombok.Getter;

@Getter
public enum ErrorCatalog {

    CLIENT_NOT_FOUND("CLIENT_NOT_FOUND", "Client not found"),

    INVALID_CLIENT("INVALID_CLIENT", "Invalid client"),

    GENERIC_ERROR("GENERIC_ERROR", "An error occurred");

    private final String code;
    private final String message;

    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
