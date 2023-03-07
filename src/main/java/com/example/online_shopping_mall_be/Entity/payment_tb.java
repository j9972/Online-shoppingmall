package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;

/*
    결제가 완료된 상태에서 주어짐
    결제(1)(외래키) : 상품(1) -> 상품마다 결제 아이디가 다르다.
    결제(N) : 결제 방식(1)
 */
@Entity
public class payment_tb {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payment_id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "product_id")
    private product_tb product_id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id")
    private payment_method_tb payment_method_id;

    @Column
    private String payment_naver; // 네이버 페이

    private String payment_kakao; // 카카오 페이

    private String payment_bank; // 무통장 입금
}
