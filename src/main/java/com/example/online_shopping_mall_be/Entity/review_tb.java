package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;

/*
    리뷰(n) : 상품(1)
    리뷰(n) : 회원(1)
 */
@Entity
public class review_tb {
    @Id @GeneratedValue
    private Long review_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private product_tb product_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private user_tb user_id;

    @Column
    private String review_title; // 리뷰 제못

    private String review_body; // 리뷰 내용

    private Integer review_counting; // 별점
}
