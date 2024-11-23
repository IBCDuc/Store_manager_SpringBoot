package com.example.demo.repository;

import com.example.demo.entity.StockInDetail;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockInDetailRepository extends JpaRepository<StockInDetail, Integer> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO stock_in_detail (quantity_add, Stock_In_id, Produc_id) values (?1, ?2, ?3) ", nativeQuery = true)
    void insertStockInDetail(Integer quantityAdd, Integer stockIn, Integer productId );

    @Query(value = "SELECT * FROM stock_in_detail WHERE Stock_In_id = ?1 and Produc_id = ?2", nativeQuery = true) 
    List<StockInDetail> checkStockInDetail(Integer stockInId, Integer productId);

    @Query(value = "SELECT * FROM stock_in_detail", nativeQuery = true)
    List<StockInDetail> findAllStockInDetail();
    
}
