package com.example.demo.repository;
import com.example.demo.entity.Products;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer > {

    @Query(value = "SELECT QuantityInStock FROM Products WHERE id = ?1", nativeQuery = true)
    Integer getQuantityInStock(Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Products SET QuantityInStock = ?1 WHERE id = ?2", nativeQuery = true)
    void updateQuantityBasedOnId(Integer quantity, Integer id);
    
    @Query(value ="SELECT * FROM Products r WHERE r.name LIKE %:searchMatiral%", nativeQuery = true)
    List<Products> searchProducts(@Param("searchMatiral") String searchMatiral);

    @Query(value = "SELECT p.id AS ProductID,p.name AS ProductName,p.price AS ProductPrice,ISNULL((SELECT SUM(sid.Quantity_add) FROM Stock_In_Detail sid JOIN Stock_In si ON sid.Stock_In_id = si.id WHERE sid.Produc_id = p.id AND si.Stock_In_Date <= ?1), 0) AS TotalStockAdded,ISNULL((SELECT SUM(od.Quantity) FROM Order_Detail od JOIN Orders o ON od.Order_id = o.id WHERE od.Products_id = p.id AND o.order_date <= ?1), 0) AS TotalStockSold,ISNULL((SELECT SUM(sid.Quantity_add) FROM Stock_In_Detail sid JOIN Stock_In si ON sid.Stock_In_id = si.id WHERE sid.Produc_id = p.id AND si.Stock_In_Date <= ?1), 0) - ISNULL((SELECT SUM(od.Quantity) FROM Order_Detail od JOIN Orders o ON od.Order_id = o.id WHERE od.Products_id = p.id AND o.order_date <= ?1), 0) AS StockRemaining FROM Products p ORDER BY p.id", nativeQuery = true)
    List<Object[]> getInventory(Date toDate);

}
