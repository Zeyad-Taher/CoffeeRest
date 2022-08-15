package com.example.coffeerest.exception;

public enum Errors {
    USER_EMAIL_NOT_FOUND("0001","User email doesn't exist"),
    USER_EMAIL_ALREADY_EXIST("0002","User email already exist"),
    USER_PASSWORD_NOT_CORRECT("0003","User password is incorrect");

    private final String code;
    private final String message;

    private Errors(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
