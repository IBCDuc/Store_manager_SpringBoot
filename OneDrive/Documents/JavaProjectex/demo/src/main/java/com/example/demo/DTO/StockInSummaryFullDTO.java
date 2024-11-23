package com.example.demo.DTO;
import java.util.List;
public class StockInSummaryFullDTO {

    private List<StockInSummaryDTO> data;
    private long total;

    public StockInSummaryFullDTO(List<StockInSummaryDTO> data, long total) {
        this.data = data;
        this.total = total;
    }

    // Getters và Setters
    public List<StockInSummaryDTO> getData() {
        return data;
    }

    public void setData(List<StockInSummaryDTO> data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
