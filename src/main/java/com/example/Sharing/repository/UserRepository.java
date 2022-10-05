package com.example.Sharing.repository;

import com.example.Sharing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByID(String ID);
}
