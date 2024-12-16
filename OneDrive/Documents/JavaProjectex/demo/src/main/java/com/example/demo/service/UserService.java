package com.example.demo.service;

import com.example.demo.entity.Users;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    

    
    public List<Users> findAll() {
        return userRepository.findAllUsers();
    }
    public List<Users> findAllAdmin() {
        return userRepository.findAllAdmin();
    }

   
    public Users findById(Integer id) {
        return userRepository.findById(id).get();
    }

   
    public Users save(Users user) {
        return userRepository.save(user);
    }

    public Users getUserEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    public Users findUserById(String email) {
        try {
            return userRepository.findUserByEmail(email);
        } catch(Exception ex) {
            System.err.println("Lỗi truy vấn: " + ex.getMessage());
            return null;  
        }
    }

    public List<Users> findAdminById(String email, String password) {
        try {
            return userRepository.findAdminByEmail(email, password);
        } catch(Exception ex) {
            System.err.println("Lỗi truy vấn: " + ex.getMessage());
            return null;  
        }
    }
    
}
