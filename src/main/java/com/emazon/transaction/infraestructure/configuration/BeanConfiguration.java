package com.emazon.transaction.infraestructure.configuration;

import com.emazon.transaction.domain.api.IPurchaseServicePort;
import com.emazon.transaction.domain.api.ISupplyServicePort;
import com.emazon.transaction.domain.spi.*;
import com.emazon.transaction.domain.usecase.PurchaseUseCase;
import com.emazon.transaction.domain.usecase.SupplyUseCase;
import com.emazon.transaction.infraestructure.configuration.jwt.JwtRequestInterceptor;
import com.emazon.transaction.infraestructure.configuration.jwt.JwtUtils;
import com.emazon.transaction.infraestructure.output.jpa.adapters.*;
import com.emazon.transaction.infraestructure.output.jpa.feign.ReportFeignClient;
import com.emazon.transaction.infraestructure.output.jpa.feign.ShoppingCartFeignClient;
import com.emazon.transaction.infraestructure.output.jpa.feign.StockFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.emazon.transaction.infraestructure.util.InfrastructureConstants.BASE_PACKAGE_FEIGN;

@Configuration
@RequiredArgsConstructor
@EnableFeignClients(basePackages = BASE_PACKAGE_FEIGN)
public class BeanConfiguration {
    private final JwtRequestInterceptor jwtRequestInterceptor;
    private final JwtUtils jwtUtils;
    private final StockFeignClient stockFeignClient;
    private final ShoppingCartFeignClient shoppingCartFeignClient;
    private final ReportFeignClient reportFeignClient;

    @Bean
    IStockPersistencePort stockPersistencePort() {
        return new StockFeignAdapter(stockFeignClient);
    }


    @Bean
    ISupplyServicePort supplyServicePort() {
        return new SupplyUseCase(stockPersistencePort());
    }

    @Bean
    IAuthenticationPersistencePort authenticationPersistencePort() {
        return new AuthenticationAdapter(jwtRequestInterceptor,jwtUtils);
    }
    @Bean
    IShoppingCartPersistencePort shoppingCartPersistencePort() {
        return new ShoppingCartFeignAdapter(shoppingCartFeignClient);
    }
    @Bean
    IReportPersistencePort reportPersistencePort() {
        return new ReportFeignAdapter(reportFeignClient);
    }

    @Bean
    IPurchaseServicePort purchaseServicePort() {
        return new PurchaseUseCase(
                authenticationPersistencePort(),
                stockPersistencePort(),
                shoppingCartPersistencePort(),
                reportPersistencePort()
        );
    }
}
