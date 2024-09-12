package com.emazon.transaction.domain.usecase;

import com.emazon.transaction.domain.api.ISupplyServicePort;
import com.emazon.transaction.domain.exeption.InvalidSupplyException;
import com.emazon.transaction.domain.model.Supply;
import com.emazon.transaction.domain.spi.ISupplyPersistencePort;

import static com.emazon.transaction.infraestructure.exceptionhandler.ExceptionResponse.SUPPLY_IS_INVALID;

public class SupplyUseCase implements ISupplyServicePort {
    public static final int MINIMUM_AMOUNT = 1;
    private final ISupplyPersistencePort supplyPersistencePort;

    public SupplyUseCase(ISupplyPersistencePort supplyPersistencePort) {
        this.supplyPersistencePort = supplyPersistencePort;
    }

    @Override
    public void addSupply(Supply supply) {
        if (supply.getAmount() < MINIMUM_AMOUNT) {
            throw new InvalidSupplyException(SUPPLY_IS_INVALID);
        }
        this.supplyPersistencePort.addSupply(supply);
    }
}
