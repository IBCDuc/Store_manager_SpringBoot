package com.example.demo.DTO;

import lombok.Data;

@Data
public class StatusReceiptDTO {
    private String receiptStatus;
    private Integer receiptCount;
    private Integer receiptQuantity;

    public StatusReceiptDTO(String receiptStatus, Integer receiptCount, Integer receiptQuantity) {
        this.receiptStatus = receiptStatus;
        this.receiptCount = receiptCount;
        this.receiptQuantity = receiptQuantity;
        
    }
}
