package com.emazon.transaction.infraestructure.output.jpa.feign;

import com.emazon.transaction.infraestructure.configuration.FeingClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.emazon.transaction.infraestructure.util.InfrastructureConstants.FEIGN_NAME;
import static com.emazon.transaction.infraestructure.util.InfrastructureConstants.FEIGN_STOCK_URL;

@FeignClient(name = FEIGN_NAME, url = FEIGN_STOCK_URL, configuration = FeingClientConfig.class)
public interface StockFeignClient {
    @PutMapping("/product/{id}/add-supply")
    void addSupply(@PathVariable Long id, @RequestParam int increment);
}
