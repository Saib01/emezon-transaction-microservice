package com.emazon.transaction.domain.spi;

import com.emazon.transaction.domain.model.Product;
import com.emazon.transaction.domain.model.Supply;

import java.util.List;

public interface IStockPersistencePort {
    List<Product> reduceStockAfterPurchase(List<Product> productRequestList);
    boolean addSupply(Supply supply);
    boolean restoreStockToPreviousStateFallback( List<Product> productRequestList);
}
