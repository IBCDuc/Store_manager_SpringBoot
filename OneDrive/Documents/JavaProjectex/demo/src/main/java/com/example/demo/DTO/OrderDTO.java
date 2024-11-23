package com.example.demo.DTO;
import java.util.List;

import com.example.demo.entity.OrderDetail;
public class OrderDTO {

    private List<OrderDetail> data;
    private long total;

    public OrderDTO(List<OrderDetail> data, long total) {
        this.data = data;
        this.total = total;
    }

    // Getters và Setters
    public List<OrderDetail> getData() {
        return data;
    }

    public void setData(List<OrderDetail> data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
