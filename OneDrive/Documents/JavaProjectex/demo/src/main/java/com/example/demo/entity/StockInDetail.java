package com.example.demo.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "Stock_In_Detail")
public class StockInDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quantityAdd;

    @ManyToOne
    @JoinColumn(name = "StockIn_id", nullable = false)
    private StockIn stockIn;

    @ManyToOne
    @JoinColumn(name = "Produc_id", nullable = false)
    private Products product;
    
    public StockInDetail() {}

    public StockInDetail(Integer quantityAdd, StockIn stockIn, Products product) {
        this.quantityAdd = quantityAdd;
        this.stockIn = stockIn;
        this.product = product;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantityAdd() {
        return quantityAdd;
    }

    public void setQuantityAdd(Integer quantityAdd) {
        this.quantityAdd = quantityAdd;
    }

    public StockIn getStockIn() {
        return stockIn;
    }

    public void setStockIn(StockIn stockIn) {
        this.stockIn = stockIn;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }
}
