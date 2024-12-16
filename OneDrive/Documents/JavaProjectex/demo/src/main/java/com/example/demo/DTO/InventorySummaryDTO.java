package com.example.demo.DTO;

import java.util.List;



public class InventorySummaryDTO {
    private List<InventoryDTO> data;
    private long total;
    public InventorySummaryDTO(List<InventoryDTO> data, long total) {
        this.data = data;
        this.total = total;
    }
    public List<InventoryDTO> getData() {
        return data;
    }

    public void setData(List<InventoryDTO> data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
