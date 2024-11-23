package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Stock_In")
public class StockIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "StockInDate")
    @Temporal(TemporalType.DATE)
    private Date stockInDate;

    @Column
    private String Status;

    @ManyToOne
    @JoinColumn(name = "Suppiler_id", nullable = false)
    private Supplier supplier;  

    @OneToMany(mappedBy = "stockIn")
    private List<StockInDetail> stockIndDetail;
    


    // Constructors
    public StockIn() {}

    public StockIn(Date stockInDate, Supplier supplier, String Status) {
        this.stockInDate = stockInDate;
        this.supplier = supplier;
        this.Status = Status;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStockInDate() {
        return stockInDate;
    }

    public void setStockInDate(Date stockInDate) {
        this.stockInDate = stockInDate;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    /* public List<StockInDetail> getStockInDetails() {
        return stockInDetail;
    }

    public void setStockInDetails(List<StockInDetail> stockInDetails) {
        this.stockInDetail = stockInDetails;
    } */
}
