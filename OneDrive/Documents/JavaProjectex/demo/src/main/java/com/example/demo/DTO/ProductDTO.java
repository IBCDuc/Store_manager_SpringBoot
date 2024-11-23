package com.example.demo.DTO;

import java.util.List;

import com.example.demo.entity.Products;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductDTO {
    private Integer productId;
    private String productName;
    private Integer productPrice;
    private Integer quantity;

    public ProductDTO(Integer productId, String productName, Integer productPrice, Integer quantity) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }
}
