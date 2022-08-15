package com.example.coffeerest.exception;

public enum Errors {
    USER_EMAIL_NOT_FOUND("0001","User email doesn't exist"), //at login
    USER_PASSWORD_NOT_CORRECT("0010","User password is incorrect"), //at login
    USER_EMAIL_ALREADY_EXIST("0011","User email already exist"), //at registration (done)
    PRODUCT_NOT_FOUND("0100","Product doesn't exist"), //at get and delete product by id (done)
    PRODUCT_IS_MISSING("0101","Product body is missing"), //at add product (done)
    FAILED_TO_UPLOAD_IMAGE("0110","Failed to upload image"), //at add image in product service
    PATH_DOESNOT_EXIST("0111","Path doesn't exist"), //at get image in product service
    PATH_IS_EMPTY("1000","Path is empty"); //at get image in product service

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
