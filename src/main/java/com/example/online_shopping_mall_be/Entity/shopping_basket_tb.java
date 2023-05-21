package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/*
    장바구니(1) : 회원(1)
    장바구니(1) : 상품(N)

 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class shopping_basket_tb {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shopping_basket_id;

    @OneToOne(mappedBy = "shopping_basket", fetch = FetchType.LAZY)
    private user_tb user_id;

    @OneToMany(mappedBy = "shopping_basket")
    private List<product_tb> products = new ArrayList<>();

    @Column
    private Integer product_amount; // 상품 수량

    private boolean payment_status; // 결제 여부
}
