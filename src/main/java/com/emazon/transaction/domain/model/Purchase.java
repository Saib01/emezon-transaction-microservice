package com.emazon.transaction.domain.model;

import com.emazon.transaction.domain.utils.TransactionStatus;

import java.util.List;

public class Purchase {
    private Long id;
    private String email;
    private List<Product> purchaseInformation;
    private Double total;
    private TransactionStatus status;

    public Purchase(Long id, String email, List<Product> purchaseInformation, Double total, TransactionStatus status) {
        this.id = id;
        this.email = email;
        this.purchaseInformation = purchaseInformation;
        this.total = total;
        this.status = status;
    }

    private Purchase(){}

    public static Purchase from(String email){
        Purchase purchase = new Purchase();
        purchase.setId(null);
        purchase.setEmail(email);
        purchase.setStatus(TransactionStatus.PENDING);
        return purchase;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Product> getPurchaseInformation() {
        return purchaseInformation;
    }

    public void setPurchaseInformation(List<Product> purchaseInformation) {
        this.purchaseInformation = purchaseInformation;
        this.total=this.purchaseInformation.stream()
                .map(product ->product.getAmount()*product.getPrice())
                .reduce(0.0, Double::sum);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}
