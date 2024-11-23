package com.example.demo.DTO;
import java.util.List;
public class OrderSummaryFullDTO {

    private List<OrderSummaryDTO> data;
    private long total;

    public OrderSummaryFullDTO(List<OrderSummaryDTO> data, long total) {
        this.data = data;
        this.total = total;
    }

    // Getters và Setters
    public List<OrderSummaryDTO> getData() {
        return data;
    }

    public void setData(List<OrderSummaryDTO> data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
