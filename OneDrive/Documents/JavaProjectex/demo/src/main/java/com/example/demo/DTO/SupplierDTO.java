package com.example.demo.DTO;
import java.util.List;

import com.example.demo.entity.Supplier;

public class SupplierDTO {

    private List<Supplier> data;
    private long total;

    public SupplierDTO(List<Supplier> data, long total) {
        this.data = data;
        this.total = total;
    }

    // Getters và Setters
    public List<Supplier> getData() {
        return data;
    }

    public void setData(List<Supplier> data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
