package com.emazon.transaction.application.handler;

import com.emazon.transaction.application.dtos.SupplyRequest;

public interface ISupplyHandler {
    void addSupply(SupplyRequest supplyRequest);
}
