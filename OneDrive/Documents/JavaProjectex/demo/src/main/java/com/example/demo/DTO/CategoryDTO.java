package com.example.demo.DTO;
import java.util.List;

import com.example.demo.entity.Category;
public class CategoryDTO {
    private List<Category> data;
    private long total;

    public CategoryDTO(List<Category> data, long total) {
        this.data = data;
        this.total = total;
    }

    // Getters and setters
    public List<Category> getData() {
        return data;
    }

    public void setData(List<Category> data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
