package com.emazon.transaction.infraestructure.configuration;

import com.emazon.transaction.domain.api.ISupplyServicePort;
import com.emazon.transaction.domain.spi.ISupplyPersistencePort;
import com.emazon.transaction.domain.usecase.SupplyUseCase;
import com.emazon.transaction.infraestructure.output.jpa.adapters.SupplyJpaAdapter;
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

    @Bean
    ISupplyPersistencePort userPersistencePort(StockFeignClient stockFeignClient) {
        return new SupplyJpaAdapter(stockFeignClient);
    }

    @Bean
    ISupplyServicePort userServicePort(ISupplyPersistencePort userPersistencePort) {
        return new SupplyUseCase(userPersistencePort);
    }


}
