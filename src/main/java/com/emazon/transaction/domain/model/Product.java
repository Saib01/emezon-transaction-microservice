package com.emazon.transaction.domain.model;


public class Product {
    private Long id;
    private String name;
    private Integer amount;
    private Double price;
    private Product() {
    }
    public Product(Long id, String name, Integer amount, Double price) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
    }
    public static Product from(Long id, Integer amount){
        Product request = new Product();
        request.setId(id);
        request.setAmount(amount);
        return request;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
