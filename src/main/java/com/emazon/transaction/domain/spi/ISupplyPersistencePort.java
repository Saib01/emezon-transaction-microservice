package com.emazon.transaction.domain.spi;

import com.emazon.transaction.domain.model.Supply;

public interface ISupplyPersistencePort {
    void addSupply(Supply supply);
}
