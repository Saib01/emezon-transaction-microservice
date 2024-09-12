package com.emazon.transaction.domain.model;

public class Supply {
    Long idProduct;
    Integer amount;

    public Supply(Long idProduct, Integer amount) {
        this.idProduct = idProduct;
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }
}
