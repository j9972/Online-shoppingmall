package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
    상품(N) : 카테고리(1)
    결제(1) : 상품(n)
    상품(1) : 상품 사진 (N)
    주문상세(1) : 상품(N)
    문의(n) : 상품(1)
    리뷰(n) : 상품(1)
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class product_tb {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private category_tb category; // FK - 카테고리명

    private String product_name; // 상품 명

    private String product_title; // 타이틀

    private String product_explaination; // 상품 설명

    private Integer product_count; // 재고 수량

    private Integer product_selling_count; // 판매 수량

    private Date product_register_date; // 상품 등록 날짜

    @Lob
    @Column(name = "product_thumbnail", columnDefinition="BLOB")
    private String product_thumbnail; // 썸네일 사진

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_detail_id")
    private order_detail_tb order_detail;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private product_tb product;

    @OneToMany(mappedBy = "product")
    private List<product_photo_tb> post_photos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "shopping_basket_id")
    private shopping_basket_tb shopping_basket;

    @OneToMany(mappedBy = "product")
    private List<ask_tb> asks = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<review_tb> reviews = new ArrayList<>();
}
