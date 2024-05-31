package com.enterprise.service.application;

/**
 * Constants class
 */

public class Constants {

    /**
     * Error codes and messages
     */

    Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String ERROR_CODE_PRICE_NOT_FOUND = "E0001";
    public static final String ERROR_MSG_PRICE_NOT_FOUND = "Prices not found.";
    public static final String ERROR_CODE_PRICE_BAD_REQUEST = "E0002";
    public static final String ERROR_MSG_PRICE_BAD_REQUEST = "Error in parameter cant be null, please check the parameters";
}
