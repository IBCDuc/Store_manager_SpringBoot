package com.example.demo.entity;

import java.util.List;
import jakarta.persistence.*;

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "quantityinstock", nullable = false)
    private int quantityinstock;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "product")
    private List<StockInDetail> stockInDetails;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Products() {}

    // Getter và Setter cho các trường hiện có
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantityinstock;
    }

    public void setQuantity(int quantity) {
        this.quantityinstock = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // Hàm mới: setCategoryId
    public void setCategoryId(Integer categoryId) {
        if (this.category == null) {
            this.category = new Category(); // Tạo đối tượng Category mới nếu chưa tồn tại
        }
        this.category.setId(categoryId); // Gán ID cho Category
    }
    
}
