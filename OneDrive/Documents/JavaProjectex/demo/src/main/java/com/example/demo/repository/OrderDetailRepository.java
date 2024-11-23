package com.example.demo.repository;
import java.util.List;

import com.example.demo.entity.OrderDetail;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query(value = "SELECT * FROM order_detail WHERE Order_id = ?1", nativeQuery = true)
    List<OrderDetail> findOrderById(Integer id);

    @Modifying
    @Query(value = "INSERT INTO order_detail (quantity, Order_id, Products_id) values (?1 , ?2, ?3)", nativeQuery = true)
    List<OrderDetail> insertData(Integer quantity, Integer id, Integer products );

    @Query(value = "SELECT * FROM order_detail WHERE Order_id = ?1 and Products_id = ?2", nativeQuery = true) 
    List<OrderDetail> checkOrderDetail(Integer id, Integer product_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE order_detail SET quantity = ?1 WHERE Order_id = ?2 and Products_id = ?3", nativeQuery = true)
    int insertQuantity(Integer quantity, Integer order_id, Integer product_id);
    
    @Query(value = "SELECT SUM(p.price * od.quantity) FROM order_detail od INNER JOIN Products p ON od.Products_id = p.id WHERE od.order_id = ?1", nativeQuery = true)
    int getTotalPriceByOrderId(Integer order_id);

    @Query(value ="select Quantity from Order_detail od  where order_id = ?1", nativeQuery = true)
    List<Object[]> geQuantityOrder(Integer order_id);

    @Query(value ="select Products_id from Order_detail od inner join Products p on p.id = od.Products_id where order_id = ?1", nativeQuery = true)
    List<Object[]> getIdOrder(Integer order_id);
    
    
}
     