package com.enterprise.service.infrastructure.utils;

/**
 * Constants for Swagger documentation
 */
public class SwaggerConstants {

    /* VALIDATION CONSTANTS */
    public static final short BRAND_ID_MAX_VALUE = Short.MAX_VALUE;
    public static final short BRAND_ID_MIN_VALUE = 1;
    public static final String BRAND_ERROR_MESSAGE = "Brand ID must be greater than or equal to "
            + BRAND_ID_MIN_VALUE + " and less than or equal to " + BRAND_ID_MAX_VALUE;
    public static final long PRODUCT_ID_MAX_VALUE = Long.MAX_VALUE;
    public static final long PRODUCT_ID_MIN_VALUE = 1L;
    public static final String PRODUCT_ERROR_MESSAGE = "Product ID must be greater than or equal to "
            + PRODUCT_ID_MIN_VALUE + " and less than or equal to " + PRODUCT_ID_MAX_VALUE;
    public static final String PRICE_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String PRICE_DATE_FORMAT_DESCRIPTION = "Date in ISO 8601 format: ";

    private SwaggerConstants() {
    }

}
