package com.example.demo.DTO;


import java.util.List;


import com.example.demo.entity.StockIn;
import com.example.demo.entity.StockInDetail;

public class StockInRequest {
    private StockIn stockIn;
    private List<StockInDetail> stockInDetails;

    public StockIn getStockIn() {
        return stockIn;
    }

    public void setStockIn(StockIn stockIn) {
        this.stockIn = stockIn;
    }

    public List<StockInDetail> getStockInDetails() {
        return stockInDetails;
    }

    public void setStockInDetails(List<StockInDetail> stockInDetails) {
        this.stockInDetails = stockInDetails;
    }
}
