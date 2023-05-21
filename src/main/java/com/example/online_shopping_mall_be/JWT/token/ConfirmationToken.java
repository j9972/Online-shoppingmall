package com.example.online_shopping_mall_be.JWT.token;

import com.example.online_shopping_mall_be.DTO.userRegisterDto;
import com.example.online_shopping_mall_be.Entity.user_tb;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String token;

    @NonNull
    private LocalDateTime createdAt;

    @NonNull
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;

    /*
            단방향
      */
    @ManyToOne
    @NonNull
    @JoinColumn(name = "user_id")
    private user_tb user;


    public ConfirmationToken(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expiresAt,
                             user_tb user) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.user = user;
    }

    //public ConfirmationToken(userRegisterDto request) {}
}
