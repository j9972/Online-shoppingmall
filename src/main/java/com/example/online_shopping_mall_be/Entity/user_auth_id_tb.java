package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;
import lombok.*;

/*
    회원(1) : 회원 인증(1)
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class user_auth_id_tb {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auth_user_id;

    private String user_auth_email; // 유저 이메일

    @OneToOne(mappedBy = "auth_user", fetch = FetchType.LAZY)
    private user_tb user_id;

    @Column
    private String user_password; // 유저 비번
}
