package com.emazon.transaction.domain.spi;

import com.emazon.transaction.domain.model.Purchase;

public interface IReportPersistencePort {
    boolean save(Purchase purchase);
}
