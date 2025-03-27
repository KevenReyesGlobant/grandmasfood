package org.grandmasfood.springcloud.users.utils;

import lombok.Getter;

@Getter
public enum ErrorCatalog {

    USER_NOT_FOUND("USER_NOT_FOUND", "User not found"),

    INVALID_USER("INVALID_USER", "Invalid user"),

    DUPLICATED_USER_DATA("DUPLICATED_USER_DATA", "Duplicated client data"),

    USER_DOCUMENT_DATA("FORMAT_DOCUMENT_INVALID", "Invalid client data"),

    GENERIC_ERROR("GENERIC_ERROR", "An error occurred");

    private final String code;
    private final String message;

    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
