package com.emazon.transaction.domain.api;

import com.emazon.transaction.domain.model.Purchase;

import java.util.List;

public interface IPurchaseServicePort {
    Purchase createPurchase(List<Long> shoppingCartIdList);
}
