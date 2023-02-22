package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
    문의(N) : 회원(1)
    문의(1) : 상품(N)
 */
@Entity
public class ask_tb {
    @Id @GeneratedValue
    private Long ask_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private user_tb user_id;

    @OneToMany(mappedBy = "ask_id")
    private List<product_tb> products = new ArrayList<>();

    @Column
    private String ask_body;

    private String ask_title;

    @Lob
    private String product_photo;

    private Date ask_register_date;

    private Date ask_update_date;
}
