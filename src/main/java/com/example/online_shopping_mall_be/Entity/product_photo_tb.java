package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;
import lombok.*;

/*
    상품(1) : 상품 사진 (N)
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class product_photo_tb {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_photo_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private product_tb product;

    @Lob
    private String product_photo; // 상품 사진

}
