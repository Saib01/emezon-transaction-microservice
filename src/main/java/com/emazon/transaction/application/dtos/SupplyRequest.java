package com.emazon.transaction.application.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SupplyRequest {
    Long idProduct;
    Integer amount;

    public SupplyRequest(Long idProduct, Integer amount) {
        this.idProduct = idProduct;
        this.amount = amount;
    }
}
