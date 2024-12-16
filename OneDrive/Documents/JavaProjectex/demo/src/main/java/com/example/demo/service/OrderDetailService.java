package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.Products;
import com.example.demo.exception.InsufficientQuantityException;
import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<OrderDetail>  findAll() {
        return orderDetailRepository.findAll();
    }
    public List<OrderDetail> findOrderById(Integer id) {
        return orderDetailRepository.findOrderById(id);
    }


    public void addProductToCart(Integer quantity, Integer id, Integer products) {
        try {
            if ( isQuantityValid(quantity, id, products) ) {
                handleAddAndUpdate(quantity, id, products);
            }
        }
        catch (InsufficientQuantityException ex) {
            System.out.println("Số lượng yêu cầu vượt quá số lượng tồn kho: " + ex.getMessage());
            throw ex;
        } catch(Exception ex) {
            System.out.println("Loi tai service" + ex);
        } 
    }
    private boolean isQuantityValid(Integer quantity, Integer id, Integer products) throws InsufficientQuantityException {
        Products currentProduct = productRepository.findById(products).get();
        List<OrderDetail> orderDetail = orderDetailRepository.checkOrderDetail(id, products);
    
        if (orderDetail.isEmpty()) {
            if (quantity > currentProduct.getQuantity()) {
                throw new InsufficientQuantityException("Số lượng yêu cầu vượt quá số lượng tồn kho");
            }
        } else {
            Integer currentQuantity = orderDetail.get(0).getQuantity() + quantity;
            if (currentQuantity > currentProduct.getQuantity()) {
                throw new InsufficientQuantityException("Số lượng yêu cầu vượt quá số lượng tồn kho");
            }
        }
        return true;
    }
    private void handleAddAndUpdate(Integer quantity, Integer id, Integer products) {
        List<OrderDetail> orderDetail = orderDetailRepository.checkOrderDetail(id, products); 
        if (orderDetail.isEmpty()) {
            orderDetailRepository.insertData(quantity, id, products);
            System.out.println("add thanh cong");
        } else {
            Integer current_quantity = orderDetail.get(0).getQuantity() + quantity;
            orderDetailRepository.insertQuantity(current_quantity, id, products);
            System.out.println("insert thanh cong");
        }
    }
    public int getTotalPrice(Integer order_id) {
        try {
            return orderDetailRepository.getTotalPriceByOrderId(order_id);
        } catch (Exception ex) {
            System.out.println("loi price " + ex);
        }
        return 0;
    }
    private void deleteQuantityWhenBuy(Integer order_id) {
        try {
            List<Object[]> productOrder = orderDetailRepository.geQuantityOrder(order_id);
            List<Object[]> productId = orderDetailRepository.getIdOrder(order_id);
            
            for (int i = 0; i < productId.size(); i++) {
                // find each order 
                Object[] elementId = productId.get(i);
                Object[] elementOrder = productOrder.get(i);
                int id = (int) elementId[0];
                int quantityInOrder = (int) elementOrder[0];
                int quantityInProduct  = productRepository.getQuantityInStock(id);
                System.out.println(quantityInProduct);
                int calculateQuanttity = quantityInProduct - quantityInOrder;
                productRepository.updateQuantityBasedOnId(calculateQuanttity, id);
            }
            
        } catch(Exception ex) {
            System.out.println("lỗi tại dòng orderDetail " + ex );
        }
        // return 0;
    }
    private void deleteOrderWhenDel(Integer order_id) {
        orderRepository.completeCart(order_id);
    }

    public void handleDeleteCart(Integer order_id) {
        try {
            deleteQuantityWhenBuy(order_id);
            deleteOrderWhenDel(order_id);
        } catch(Exception ex) {
            System.out.println("loi tai logic tong hop " + ex);
        }
        
    }   
    
    
}
