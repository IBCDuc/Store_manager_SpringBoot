package com.example.demo.controller;

import com.example.demo.DTO.AdminDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.Users;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;






@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping
    public UserDTO getAllUsers() {
        List<Users> users = userService.findAll(); // Lấy danh sách người dùng
        long total = users.size(); // Tổng số người dùng (hoặc lấy từ database)
        return new UserDTO(users, total);
    }

    @GetMapping("/admin")
    public AdminDTO getAllAdmins() {
        List<Users> users = userService.findAllAdmin(); // Lấy danh sách người dùng
        long total = users.size(); // Tổng số người dùng (hoặc lấy từ database)
        return new AdminDTO(users, total);
    }
    

    // @GetMapping("/validate/{id}")
    // public ResponseEntity<?> findUserById(@PathVariable Integer id) {
    //     try {
    //         Users user = userService.findUserById(id);
            
    //         return ResponseEntity.ok(user);
    //     } catch(Exception ex) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something err somewhere");
    //     }
        
    // }

    @PostMapping("/validate/login")
    public ResponseEntity<?> validateuser(@RequestBody Users users) {
        try {
            Users user = userService.findUserById(users.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something err somewhere");
        }
    }
    @PostMapping("/validate/admin")
    public ResponseEntity<?> validateAdmin(@RequestBody Users users) {
        try {
            Users user = userService.findAdminById(users.getEmail(), users.getPassword());
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something err somewhere");
        }
    }
    
    


    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody Users users) {
        String email = users.getEmail();
        
        if (userService.getUserEmail(email) == null) {
            // Lưu người dùng vào cơ sở dữ liệu
            userService.save(users);

            // Trả về mã trạng thái 201 và thông tin người dùng mới
            return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of(
                    "status", 201,
                    "message", "User added successfully",
                    "user", Map.of(
                        "id", users.getId(),
                        "name", users.getName(),
                        "email", users.getEmail(),
                        "role", users.getRole()
                    )
                )
            );
        } else {
            // Nếu email đã tồn tại, trả về thông báo lỗi
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of(
                    "status", 400,
                    "message", "Email already exists"
                )
            );
        }
    }




    // !!! Cần thêm api để đưa vào Request Body
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody String entity) {
        Map < String, Object > respEmp = new LinkedHashMap< String, Object> ();
        try {
            Users user = userService.findById(id);
            user.setName("duc234");
            userService.save(user);
            respEmp.put("message", "susscessfull");
            return new ResponseEntity<> (respEmp, HttpStatus.OK);
        }
        catch(NoSuchElementException err) {
            respEmp.clear();
            respEmp.put("message", "User data not found");
            return new ResponseEntity<> (respEmp, HttpStatus.NOT_FOUND);
        } 
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam Integer id) {
        
        Map < String, Object > respEmp = new LinkedHashMap < String, Object > ();
        try {
            userService.deleteById(id);
            respEmp.put("message", "Successfully!!!");
            return new ResponseEntity<>(respEmp, HttpStatus.OK);
        } catch(Exception ex) {
            respEmp.clear();
            respEmp.put("message", "User data not found");
            return new ResponseEntity<>(respEmp, HttpStatus.OK);
        }
    }
    
}
