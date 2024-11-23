package com.example.demo.DTO;
import java.sql.Date;
import java.util.*;

import lombok.Data;


@Data
public class StockInSummaryDTO {
    private Integer stockInId;        
    private Date stockInDate; 
    private String status;  
    private Integer supplierId;
    private String supplierName; 
    private String supplierPhone;
    private Integer totalAmount; 
    private List<ProductDTO> products;
    // Constructor
    public StockInSummaryDTO(Integer stockInId, Date stockInDate, String status, Integer supplierId, String supplierName, String supplierPhone, Integer totalAmount, List<ProductDTO> products) {
        this.stockInId = stockInId;
        this.stockInDate = stockInDate;
        this.status = status;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierPhone = supplierPhone;
        this.totalAmount = totalAmount;
        this.products = products;
    }
    public List<ProductDTO> getProducts() {
        return products;
    }
    // Getters and Setters
    public Integer getstockInId() {
        return stockInId;
    }

    public void setstockInId(Integer stockInId) {
        this.stockInId = stockInId;
    }

    public Date getstockInDate() {
        return stockInDate;
    }

    public void setstockInDate(Date stockInDate) {
        this.stockInDate = stockInDate;
    }



    public String getsupplierName() {
        return supplierName;
    }

    public void setsupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setsupplierId(Integer stockInId) {
        this.stockInId = stockInId;
    }

    public Integer getsupplierId() {
        return supplierId;
    }

    public void setsupplierPhone(Integer stockInId) {
        this.stockInId = stockInId;
    }

    public String getsupplierPhone() {
        return supplierPhone;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "OrderSummaryDTO{" +
                "stockInId=" + stockInId +
                ", stockInDate=" + stockInDate +
                ", supplierName='" + supplierName + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
