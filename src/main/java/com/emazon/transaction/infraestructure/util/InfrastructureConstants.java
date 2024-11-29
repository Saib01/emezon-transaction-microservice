package com.emazon.transaction.infraestructure.util;

public class InfrastructureConstants {

    public static final String BEARER_PREFIX = "Bearer ";
    public static final String AUTHORITIES = "authorities";
    public static final String JWT_KEY_GENERATOR = "${security.jwt.key.private}";
    public static final String JWT_USER_GENERATOR = "${security.jwt.user.generator}";

    public static final String AUX_BODEGA = "AUX_BODEGA";
    public static final String CLIENT="CLIENT";
    public static final int JWT_TIME_EXPIRATION = 1000 * 60 * 24;

    public static final String TEMPLATE_RESPONSE_ERROR = "{\"message\": \"%s\"}";


    public static final String BASE_PACKAGE_FEIGN = "com.emazon.transaction.infraestructure.output.jpa.feign";
    public static final String FEIGN_STOCK_NAME = "microservice-stock";
    public static final String FEIGN_STOCK_URL = "${external.stock.api}";
    public static final String FEIGN_SHOPPING_CART_NAME = "microservice-shopping-cart";
    public static final String FEIGN_SHOPPING_CART_URL = "${external.shopping.cart.api}";
    public static final String FEIGN_REPORT_NAME = "microservice-report";
    public static final String FEIGN_REPORT_URL = "${external.report.api}";
    private InfrastructureConstants() {
    }
}
