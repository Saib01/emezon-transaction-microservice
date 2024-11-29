package com.emazon.transaction.infraestructure.output.jpa.adapters;

import com.emazon.transaction.domain.model.Product;
import com.emazon.transaction.domain.model.Supply;
import com.emazon.transaction.domain.spi.IStockPersistencePort;
import com.emazon.transaction.infraestructure.exceptionhandler.ConnectionRefused;
import com.emazon.transaction.infraestructure.output.jpa.feign.StockFeignClient;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class StockFeignAdapter implements IStockPersistencePort {
    private final StockFeignClient stockFeignClient;

    @Override
    public List<Product> reduceStockAfterPurchase(List<Product> productRequestList){

        try {
            return this.stockFeignClient.reduceStockAfterPurchase(productRequestList);
        } catch (RuntimeException e) {
            ConnectionRefused.throwIfConnectionRefused(e.getMessage());
            return List.of();
        }
    }
    @Override
    public boolean addSupply(Supply supply) {
        try {
            this.stockFeignClient.addSupply(supply.getIdProduct(), supply.getAmount());
            return true;
        } catch (RuntimeException e) {
            ConnectionRefused.throwIfConnectionRefused(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean restoreStockToPreviousStateFallback(List<Product> productRequestList) {
        try {
            this.stockFeignClient.restoreStockToPreviousStateFallback(productRequestList);
            return true;
        } catch (RuntimeException e) {
            ConnectionRefused.throwIfConnectionRefused(e.getMessage());
            return false;
        }
    }
}
