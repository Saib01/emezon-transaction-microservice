package com.emazon.transaction.application.handler.implement;

import com.emazon.transaction.application.handler.IPurchaseHandler;
import com.emazon.transaction.domain.api.IPurchaseServicePort;
import com.emazon.transaction.domain.model.Purchase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class PurchaseHandler implements IPurchaseHandler {
    private final IPurchaseServicePort purchaseServicePort;
    @Override
    public Purchase createPurchase(List<Long> shoppingCartIdList) {
        return  this.purchaseServicePort.createPurchase(shoppingCartIdList);
    }
}
