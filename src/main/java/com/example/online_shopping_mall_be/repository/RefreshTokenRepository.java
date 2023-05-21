package com.example.online_shopping_mall_be.repository;

import com.example.online_shopping_mall_be.Entity.refreshToken_tb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<refreshToken_tb,Long> {
    Optional<refreshToken_tb> findByRefreshToken(String refreshToken);
}
