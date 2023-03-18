package com.example.online_shopping_mall_be.Entity;

// TODO: 2023/03/18 컬럼 @notnull 처리해야하는 데이터 체크 잘하기

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
    문의(N) : 회원(1)
    문의(N) : 상품(1)
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ask_tb {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ask_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private user_tb user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private product_tb product;

    @Column
    private String ask_body; // 문의 내용

    private String ask_title; // 문의 제못

    @Lob
    private String product_photo; // 상품 사진

    private Date ask_register_date; // 문의 날짜

    private Date ask_update_date; // 문의 수정 일
}
