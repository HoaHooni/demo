package com.example.demo.auth.repository;

import com.example.demo.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);
    Boolean existsByUserName(String username);
    Boolean existsByEmail(String email);

    @Query(value = "select * from User where user = ?", nativeQuery = true)
    List<User> findAbe(String str);
}
