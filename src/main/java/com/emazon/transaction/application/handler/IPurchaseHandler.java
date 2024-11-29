package com.emazon.transaction.application.handler;

import com.emazon.transaction.domain.model.Purchase;

import java.util.List;

public interface IPurchaseHandler
{
    Purchase createPurchase(List<Long> shoppingCartIdList);
}
