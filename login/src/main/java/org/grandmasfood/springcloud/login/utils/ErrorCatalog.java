package org.grandmasfood.springcloud.login.utils;

import lombok.Getter;

@Getter
public enum ErrorCatalog {

    LOGIN_NOT_FOUND("LOGIN_NOT_FOUND", "Login not found"),

    INVALID_LOGIN("INVALID_LOGIN", "Invalid login"),

    DUPLICATED_LOGIN_DATA("DUPLICATED_LOGIN_DATA", "Duplicated login data"),

    LOGIN_DOCUMENT_DATA("FORMAT_DOCUMENT_INVALID", "Invalid login data"),

    GENERIC_ERROR("GENERIC_ERROR", "An error occurred");

    private final String code;
    private final String message;

    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
