package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/*
    장바구니(1) : 회원(1)
    장바구니(1) : 상품(N)

 */
@Entity
public class shopping_basket_tb {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shopping_basket_id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "user_id")
    private user_tb user_id;

    @OneToMany(mappedBy = "shopping_basket_id")
    private List<product_tb> products = new ArrayList<>();

    @Column
    private Integer product_amount; // 상품 수량

    private boolean payment_status; // 결제 여부
}
