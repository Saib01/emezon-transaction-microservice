package com.emazon.transaction.domain.usecase;

import com.emazon.transaction.domain.api.ISupplyServicePort;
import com.emazon.transaction.domain.exeption.InvalidIdProductException;
import com.emazon.transaction.domain.exeption.InvalidSupplyException;
import com.emazon.transaction.domain.exeption.ProductNotFoundException;
import com.emazon.transaction.domain.model.Supply;
import com.emazon.transaction.domain.spi.IStockPersistencePort;

import static com.emazon.transaction.domain.utils.ExceptionResponseDomain.*;


public class SupplyUseCase implements ISupplyServicePort {
    public static final int MINIMUM_VALUE = 1;
    private final IStockPersistencePort stockPersistencePort;

    public SupplyUseCase(IStockPersistencePort stockPersistencePort) {
        this.stockPersistencePort = stockPersistencePort;
    }

    @Override
    public void addSupply(Supply supply) {
        if (supply.getAmount() < MINIMUM_VALUE) {
            throw new InvalidSupplyException(SUPPLY_IS_INVALID);
        }
        if (supply.getIdProduct()< MINIMUM_VALUE) {
            throw new InvalidIdProductException(ID_PRODUCT_IS_INVALID);
        }
        if(!this.stockPersistencePort.addSupply(supply)){
            throw new ProductNotFoundException(STOCK_FEIGN_PRODUCT_NOT_FOUND);
        }
    }
}
