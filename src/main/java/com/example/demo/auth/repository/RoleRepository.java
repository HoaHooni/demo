package com.example.demo.auth.repository;

import com.example.demo.auth.domain.ERole;
import com.example.demo.auth.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
