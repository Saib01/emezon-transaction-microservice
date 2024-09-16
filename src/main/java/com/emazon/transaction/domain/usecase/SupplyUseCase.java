package com.emazon.transaction.domain.usecase;

import com.emazon.transaction.domain.api.ISupplyServicePort;
import com.emazon.transaction.domain.exeption.InvalidIdProductException;
import com.emazon.transaction.domain.exeption.InvalidSupplyException;
import com.emazon.transaction.domain.model.Supply;
import com.emazon.transaction.domain.spi.ISupplyPersistencePort;

import static com.emazon.transaction.domain.utils.ExceptionResponseDomain.ID_PRODUCT_IS_INVALID;
import static com.emazon.transaction.domain.utils.ExceptionResponseDomain.SUPPLY_IS_INVALID;


public class SupplyUseCase implements ISupplyServicePort {
    public static final int MINIMUM_VALUE = 1;
    private final ISupplyPersistencePort supplyPersistencePort;

    public SupplyUseCase(ISupplyPersistencePort supplyPersistencePort) {
        this.supplyPersistencePort = supplyPersistencePort;
    }

    @Override
    public void addSupply(Supply supply) {
        if (supply.getAmount() < MINIMUM_VALUE) {
            throw new InvalidSupplyException(SUPPLY_IS_INVALID);
        }
        if (supply.getIdProduct()>= MINIMUM_VALUE) {
            throw new InvalidIdProductException(ID_PRODUCT_IS_INVALID);
        }
        this.supplyPersistencePort.addSupply(supply);
    }
}
