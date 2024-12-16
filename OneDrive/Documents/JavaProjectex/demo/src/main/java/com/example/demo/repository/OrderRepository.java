package com.example.demo.repository;

import com.example.demo.DTO.OrderSummaryDTO;
import com.example.demo.entity.Orders;
import java.sql.*;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
    
    @Query(value = "SELECT * FROM orders WHERE user_id = ?1 ", nativeQuery = true )
    List<Orders> checkOrder(Integer id);

    @Query(value = "SELECT TOP 1 PERCENT id FROM orders WHERE user_id = ?1 ", nativeQuery = true )
    Integer getOrderId(Integer id);

    
    @Modifying
    @Query(value = "INSERT INTO orders (order_date, user_id, status) values (?2, ?1, 'Pending') ", nativeQuery = true)
    List<Orders> insertOrder(Integer id, Date date);

    @Query(value = "SELECT status FROM orders where id = ?1", nativeQuery = true) 
    String getStatusOrder(Integer id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE orders SET status = 'Completed' WHERE id = ?1", nativeQuery = true )
    void completeCart(Integer order_id); 

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM orders where id = ?1 ", nativeQuery = true )
    void deleteOrder(Integer order_id);

    @Query(value = "SELECT o.id AS order_id, o.order_date, o.status AS order_status, u.id AS customer_id, u.name AS customer_name,u.Email AS customer_email, SUM(od.Quantity * p.price) AS total_price,p.id AS product_id,p.name AS product_name,p.price AS product_price,od.Quantity AS quantity FROM Orders o INNER JOIN Users u ON o.user_id = u.id INNER JOIN Order_detail od ON o.id = od.order_id INNER JOIN Products p ON od.products_id = p.id WHERE (:id IS NULL OR CAST(o.id AS CHAR) LIKE %:id%) GROUP BY o.id, o.order_date, o.status, u.id, u.name, u.Email, p.id, p.name, p.price, od.Quantity ORDER BY o.order_date DESC", nativeQuery = true)
    List<Object[]> getOrderSummary(@Param("id") String id);

    @Query(value = "SELECT SUM(od.quantity * p.price) AS total_order_value FROM Order_detail od INNER JOIN Products p ON od.products_id = p.id INNER JOIN Orders o ON od.order_id = o.id WHERE o.status = 'Completed' ", nativeQuery = true )
    Integer getAllPriceOrder();

    @Query(value ="SELECT o.Status AS stockInStatus, COUNT(DISTINCT o.id) AS orderCount, SUM(od.Quantity) AS totalQuantity  FROM Orders o INNER JOIN Order_detail od ON o.id = od.Order_id GROUP BY o.status ORDER BY o.status", nativeQuery = true)
    List<Object[]> getCountStatus();

} 
