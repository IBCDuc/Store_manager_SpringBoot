package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import com.example.demo.entity.Products;
import com.example.demo.entity.StockIn;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface StockInRepository extends JpaRepository<StockIn, Integer> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO stock_in ( Stock_In_Date, Suppiler_id) VALUES (?1, ?2 )", nativeQuery = true)
    void insertSupCart( Date date, Integer supId);

    @Query(value = "SELECT id FROM stock_in WHERE  Suppiler_id = ?1", nativeQuery = true)
    List<Integer> findSup(Integer supId);

    @Query(value = "SELECT * FROM stock_in WHERE Suppiler_id = ?1 AND Status != 'Completed' ", nativeQuery = true)
    StockIn checkStatusCart(Integer supId);

    @Query(value = "SELECT si.id AS stockInId,si.Stock_In_Date AS stockInDate, si.Status AS status , si.Suppiler_id AS supplierId,s.name AS supplierName, s.phone AS supplierPhone, SUM(sid.Quantity_add * p.price) AS totalPrice, p.id AS productId,p.name AS productName,p.price AS productPrice,sid.Quantity_add AS productQuantity FROM Stock_In si LEFT JOIN Stock_In_Detail sid ON si.id = sid.Stock_In_id LEFT JOIN Products p ON sid.Produc_id = p.id LEFT JOIN Supplier s ON si.Suppiler_id = s.id  GROUP BY si.id, si.Stock_In_Date, si.Suppiler_id, s.name, s.phone, p.id, p.name, p.price, sid.Quantity_add, si.Status ORDER BY si.Stock_In_Date DESC", nativeQuery = true)
    List<Object[]> getSummaryStockIn();

    @Query(value ="SELECT si.id AS stockInId,si.Stock_In_Date AS stockInDate, si.Status AS status , si.Suppiler_id AS supplierId,s.name AS supplierName, s.phone AS supplierPhone, SUM(sid.Quantity_add * p.price) AS totalPrice, p.id AS productId,p.name AS productName,p.price AS productPrice,sid.Quantity_add AS productQuantity FROM Stock_In si LEFT JOIN Stock_In_Detail sid ON si.id = sid.Stock_In_id LEFT JOIN Products p ON sid.Produc_id = p.id LEFT JOIN Supplier s ON si.Suppiler_id = s.id WHERE (:id IS NULL OR CAST(si.id AS CHAR) LIKE %:id%) GROUP BY si.id, si.Stock_In_Date, si.Suppiler_id, s.name, s.phone, p.id, p.name, p.price, sid.Quantity_add, si.Status ORDER BY si.Stock_In_Date DESC", nativeQuery = true)
    List<Object[]> searchSummaryStock(@Param("id") String id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Stock_In Set Status = ?1 WHERE id = ?2", nativeQuery = true)
    void changeStatusCompleted(String status, Integer id);

    @Query(value = "SELECT si.Status AS stockInStatus,COUNT(DISTINCT si.id) AS stockInCount, SUM(sid.Quantity_add) AS totalQuantity FROM Stock_In si     INNER JOIN Stock_In_Detail sid ON si.id = sid.Stock_In_id GROUP BY si.Status ORDER BY si.Status", nativeQuery = true)
    List<Object[]> getCountStatusRe();

    @Query(value = "SELECT SUM(p.price * sid.Quantity_add) AS TotalSalePrice FROM Products p INNER JOIN Stock_In_Detail sid ON p.id = sid.Produc_id INNER JOIN Stock_In si ON sid.Stock_In_id = si.id WHERE si.Status = 'Completed' ",nativeQuery = true)
    Integer getAllPriceReceipt();
}

    
