
package com.example.demo.DTO;
import java.util.List;

import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.Users;

public class InventoryDTO {
    private Integer productId;
    private String productName;
    private Integer productPrice;
    private Integer totalStockAdded;
    private Integer totalStockSold;
    private Integer stockRemaining;

    // Default constructor
    public InventoryDTO() {
    }

    // Parameterized constructor
    public InventoryDTO(Integer productId, String productName, Integer productPrice,
                         Integer totalStockAdded, 
                        Integer totalStockSold, Integer stockRemaining) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;

        this.totalStockAdded = totalStockAdded;
        this.totalStockSold = totalStockSold;
        this.stockRemaining = stockRemaining;
    }

    // Getters and Setters
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }


    public Integer getTotalStockAdded() {
        return totalStockAdded;
    }

    public void setTotalStockAdded(Integer totalStockAdded) {
        this.totalStockAdded = totalStockAdded;
    }
    public Integer getTotalStockSold() {
        return totalStockSold;
    }

    public void setTotalStockSold(Integer totalStockSold) {
        this.totalStockSold = totalStockSold;
    }

    public Integer getStockRemaining() {
        return stockRemaining;
    }

    public void setStockRemaining(Integer stockRemaining) {
        this.stockRemaining = stockRemaining;
    }
}
       
