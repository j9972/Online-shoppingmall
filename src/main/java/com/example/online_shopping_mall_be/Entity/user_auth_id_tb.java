package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;

@Entity
public class user_auth_id_tb {

    @Id @GeneratedValue
    private String user_auth_email;

    @Column
    private Integer user_id;

    private String user_password;
}

//product ( 1 ) : @OneToMany(mappedBy = "member")
//
//comment ( n ) : @ManyToOne(fetch = FetchType.LAZY)  @JoinColumn(name = "")