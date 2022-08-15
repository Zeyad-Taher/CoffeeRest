package com.example.coffeerest.exception;

public enum Errors {
    USER_EMAIL_NOT_FOUND("0001","User email doesn't exist"), //at login
    USER_PASSWORD_NOT_CORRECT("0002","User password is incorrect"), //at login
    USER_EMAIL_ALREADY_EXIST("0003","User email already exist"), //at registration (done)
    PRODUCT_NOT_FOUND("0004","Product doesn't exist"); //at get and delete product by id (done)

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
