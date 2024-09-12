package com.emazon.transaction.infraestructure.output.jpa.adapters;

import com.emazon.transaction.domain.model.Supply;
import com.emazon.transaction.domain.spi.ISupplyPersistencePort;
import com.emazon.transaction.infraestructure.output.jpa.feign.StockFeignClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SupplyJpaAdapter implements ISupplyPersistencePort {
    private final StockFeignClient stockFeignClient;


    @Override
    public void addSupply(Supply supply) {
        this.stockFeignClient.addSupply(supply.getIdProduct(), supply.getAmount());
    }
}
