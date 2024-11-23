package com.example.demo.repository;
import java.util.List;
import com.example.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    List<Users> findByName(String name);
    @Query(value = "SELECT * FROM users where id = ?1", nativeQuery = true )
    Users findUserById(Integer id);

    @Query(value= "SELECT * FROM users WHERE email = ?1", nativeQuery = true) 
    Users findUserByEmail(String email);

    @Query(value= "SELECT * FROM users WHERE email = ?1 AND password = ?2 AND role = 1", nativeQuery = true)
    Users findAdminByEmail(String email, String password);

    @Query(value = "SELECT * FROM users WHERE role = 1", nativeQuery = true)
    List<Users> findAllAdmin();

}
