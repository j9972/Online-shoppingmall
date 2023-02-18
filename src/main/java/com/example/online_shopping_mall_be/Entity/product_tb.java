package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;

import java.util.Date;
/*
    상품(N) : 카테고리(1)
 */
@Entity
public class product_tb {
    @Id @GeneratedValue
    private Long product_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private category_tb category_id;


    private String product_name;

    private String product_title;

    private String product_explaination;

    private Integer product_count;

    private Integer product_selling_count;

    private Date product_register_date;

    @Lob
    @Column(name = "product_thumbnail", columnDefinition="BLOB")
    private byte[] product_thumbnail;
}
