package com.emazon.transaction.infraestructure.util;

public class InfraestructureRestControllerConstants {
    public static final String UPDATE_SUPPLY_OPERATION_SUMMARY = "Update a supply";
    public static final String RESPONSE_CODE_SUCCESS = "200";
    public static final String RESPONSE_CODE_UNAUTHORIZED = "401";
    public static final String RESPONSE_CODE_404 = "404";
    public static final String RESPONSE_CODE_400 = "400";
    public static final String RESPONSE_DESCRIPTION_UPDATED_SUCCESSFUL = "Supply updated successfully";
    public static final String RESPONSE_DESCRIPTION_UNAUTHORIZED = "Unauthorized";
    public static final String SUPPLY_NOT_FOUND_DESCRIPTION = "Supply not found";
    public static final String INVALID_REQUEST_DESCRIPTION = "Invalid request";

    public static final String API_BASE = "/api";
    public static final String SUPPLY = "/supply";
    public static final String API_ADD_SUPPLY = String.format("%s%s", API_BASE, SUPPLY);

    private InfraestructureRestControllerConstants() {

    }
}
