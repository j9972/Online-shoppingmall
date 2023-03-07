package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;

/*
    회원(1) : 회원 인증(1)
 */
@Entity
public class user_auth_id_tb {

    @Id
    private String user_auth_email; // 유저 이메일

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "user_id")
    private user_tb user_id;

    @Column
    private String user_password; // 유저 비번
}
