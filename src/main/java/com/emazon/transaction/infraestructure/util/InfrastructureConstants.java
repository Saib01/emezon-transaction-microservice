package com.emazon.transaction.infraestructure.util;

public class InfrastructureConstants {

    public static final String BEARER_PREFIX = "Bearer ";
    public static final String AUTHORITIES = "authorities";
    public static final String JWT_KEY_GENERATOR = "${security.jwt.key.private}";
    public static final String JWT_USER_GENERATOR = "${security.jwt.user.generator}";

    public static final String AUX_BODEGA = "AUX_BODEGA";
    public static final String ADD_SUPPLY = "addSupply";
    public static final int JWT_TIME_EXPIRATION = 1000 * 60 * 24;

    public static final String TEMPLATE_RESPONSE_ERROR = "{\"message\": \"%s\"}";


    public static final String BASE_PACKAGE_FEIGN = "com.emazon.transaction.infraestructure.output.jpa.feign";
    public static final String FEIGN_NAME = "microservice-stock";
    public static final String FEIGN_STOCK_URL = "${external.stock.api}";

    private InfrastructureConstants() {
    }
}
