package com.example.demo.DTO;
import java.util.List;

import com.example.demo.entity.Products;

public class ProductsDTO {

    private List<Products> data;
    private long total;

    public ProductsDTO(List<Products> data, long total) {
        this.data = data;
        this.total = total;
    }

    // Getters và Setters
    public List<Products> getData() {
        return data;
    }

    public void setData(List<Products> data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
