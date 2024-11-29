package com.emazon.transaction.infraestructure.input.rest;

import com.emazon.transaction.application.handler.IPurchaseHandler;
import com.emazon.transaction.domain.model.Purchase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/purchase")
@RequiredArgsConstructor
public class PurchaseRestController {
    private final IPurchaseHandler purchaseHandler;
    @PostMapping
    public ResponseEntity<Purchase> createPurchase(@RequestParam(name="listId", defaultValue ="") List<Long> shoppingCartIdList) {

        return ResponseEntity.ok(this.purchaseHandler.createPurchase(shoppingCartIdList));
    }

}
