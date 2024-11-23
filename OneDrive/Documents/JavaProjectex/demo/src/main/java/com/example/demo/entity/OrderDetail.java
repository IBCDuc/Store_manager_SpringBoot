package com.example.demo.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "Order_detail")
public class OrderDetail {  

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Quantity", nullable = false)
    private Integer Quantity;

    @ManyToOne
    @JoinColumn(name = "Order_id", nullable = false)
    private Orders order;  // Sử dụng entity Order

    @ManyToOne
    @JoinColumn(name = "Products_id", nullable = false)
    private Products product;  // Sử dụng entity Product

    // Constructors
    public OrderDetail() {}

    public OrderDetail(Integer quantity, Orders order, Products product) {
        this.Quantity = quantity;
        this.order = order;
        this.product = product;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        this.Quantity = quantity;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

}
