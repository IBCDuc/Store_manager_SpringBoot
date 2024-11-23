package com.example.demo.DTO;

import lombok.Data;

@Data
public class StatusOrderDTO {
    private String orderStatus;
    private Integer orderCount;
    private Integer totalQuantity;

    public StatusOrderDTO(String orderStatus, Integer orderCount, Integer totalQuantity) {
        this.orderStatus = orderStatus;
        this.orderCount = orderCount;
        this.totalQuantity = totalQuantity;
        
    }
}
