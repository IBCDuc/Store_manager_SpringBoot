package com.example.demo.entity; 

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = true, length = 100) 
    private String name;

    @Column(name = "address", nullable = true, length = 500) 
    private String address;

    @Column(name = "email", nullable = false, length = 255, unique = true) 
    private String email;

    @Column(name = "password", nullable = false, length = 1000) 
    private String password;

    @Column(name = "role") 
    private Boolean role; 

    @Column(name = "base_role",nullable = false, length = 100 )
    private String base_role;

    @OneToMany(mappedBy = "user")
    private List<Orders> orders;

    // Constructor
    public Users() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRole() {
        return role;
    }

    public void setRole(Boolean role) {
        this.role = role;
    }

    
    public String getBaseRole() {
        return base_role;
    }

    public void setBaseRole(String base_role) {
        this.base_role = base_role;
    }
}
