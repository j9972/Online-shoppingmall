package com.example.online_shopping_mall_be.Entity;

// TODO: 2023/03/18 컬럼 @notnull 처리해야하는 데이터 체크 잘하기

import jakarta.persistence.*;
import lombok.*;
//import org.jetbrains.annotations.*;

import java.util.Date;

/*
    문의(N) : 회원(1)
    문의(N) : 상품(1)
 */
@Entity
@Getter
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
    // @NotNull

    //@NotNull
    private String ask_body; // 문의 내용
    //@NotNull
    private String ask_title; // 문의 제목

    @Lob
    private String product_photo; // 상품 사진

    private Date ask_register_date; // 문의 날짜

    private Date ask_update_date; // 문의 수정 일
}
