package com.emazon.transaction.infraestructure.output.jpa.feign;

import com.emazon.transaction.domain.model.Product;
import com.emazon.transaction.infraestructure.configuration.FeingClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.emazon.transaction.infraestructure.util.InfrastructureConstants.FEIGN_STOCK_NAME;
import static com.emazon.transaction.infraestructure.util.InfrastructureConstants.FEIGN_STOCK_URL;

@FeignClient(name = FEIGN_STOCK_NAME, url = FEIGN_STOCK_URL, configuration = FeingClientConfig.class)
public interface StockFeignClient {
    @PutMapping("api/product/{id}/add-supply")
    void addSupply(@PathVariable Long id, @RequestParam int increment);
    @PutMapping("api/product/reduce-stock")
    List<Product> reduceStockAfterPurchase(@RequestBody List<Product> productRequestList);
    @PutMapping("api/product/restore-stock-fallback")
    void restoreStockToPreviousStateFallback(@RequestBody List<Product> productRequestList);
}
