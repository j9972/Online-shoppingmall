package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
    상품(N) : 카테고리(1)
    결제(1) : 상품(1) -> 상품마다 결제 아이디가 다르다.
    상품(1) : 상품 사진 (N)
    주문상세(1) : 상품(N)
    문의(1) : 상품(N)
    리뷰(n) : 상품(1)
 */
@Entity
public class product_tb {
    @Id @GeneratedValue
    private Long product_id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private category_tb category_id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_detail_id")
    private order_detail_tb order_detail_id;

    private String product_name;

    private String product_title;

    private String product_explaination;

    private Integer product_count;

    private Integer product_selling_count;

    private Date product_register_date;

    @Lob
    @Column(name = "product_thumbnail", columnDefinition="BLOB")
    private String product_thumbnail;

    @OneToOne(mappedBy = "product_id", fetch = FetchType.LAZY)
    private product_tb product;

    @OneToMany(mappedBy = "product_id")
    private List<product_photo_tb> post_photos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "shopping_basket_id")
    private shopping_basket_tb shopping_basket_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ask_id")
    private ask_tb ask_id;

    @OneToMany(mappedBy = "review_id")
    private List<review_tb> reviews = new ArrayList<>();
}
