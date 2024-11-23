package com.example.demo.DTO;
import java.util.List;

import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.Users;
public class EachProductDTO {

    private List<OrderDetail> data;
    

    public EachProductDTO(List<OrderDetail> data, long total) {
        this.data = data;
       
    }

    // Getters và Setters
    public List<OrderDetail> getData() {
        return data;
    }

    public void setData(List<OrderDetail> data) {
        this.data = data;
    }

    // public long getTotal() {
    //     return total;
    // }

    // public void setTotal(long total) {
    //     this.total = total;
    // }
}
