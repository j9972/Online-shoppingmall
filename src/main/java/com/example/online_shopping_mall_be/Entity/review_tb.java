package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;

/*
    리뷰(n) : 상품(1)
    리뷰(n) : 회원(1)
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class review_tb {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long review_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private product_tb product;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private user_tb user;

    @Column
    @NotNull
    private String review_title; // 리뷰 제못


    private String review_body; // 리뷰 내용


    private Integer review_counting; // 별점
}
