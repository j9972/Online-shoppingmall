package com.example.online_shopping_mall_be.repository;

import com.example.online_shopping_mall_be.Entity.user_tb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<user_tb, Long> {
    Optional<user_tb> findByEmail(String email);
    // user_tb findByEmailId(@Param("email") String email);


    @Transactional
    @Modifying
    @Query("UPDATE user_tb a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableUser(String email);
}
