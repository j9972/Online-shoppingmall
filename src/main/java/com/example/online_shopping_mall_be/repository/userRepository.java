package com.example.online_shopping_mall_be.repository;

import com.example.online_shopping_mall_be.Entity.user_tb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<user_tb, Long> {
}
