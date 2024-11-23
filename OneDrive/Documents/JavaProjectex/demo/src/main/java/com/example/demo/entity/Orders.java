package com.example.demo.entity;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_date")
    @Temporal(TemporalType.DATE)
    private Date order_date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

    // Constructors
    public Orders() {}

    public Orders(Date order_date, Users user) {
        this.order_date = order_date;
        this.user = user;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return order_date;
    }

    public void setOrderDate(Date orderDate) {
        this.order_date = orderDate;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
