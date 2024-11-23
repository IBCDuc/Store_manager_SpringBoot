package com.example.demo.DTO;
import java.util.List;

import com.example.demo.entity.Users;
public class AdminDTO {

    private List<Users> data;
    private long total;

    public AdminDTO(List<Users> data, long total) {
        this.data = data;
        this.total = total;
    }

    // Getters và Setters
    public List<Users> getData() {
        return data;
    }

    public void setData(List<Users> data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
