package com.emazon.transaction.domain.api;

import com.emazon.transaction.domain.model.Supply;

public interface ISupplyServicePort {
    void addSupply(Supply supply);
}
