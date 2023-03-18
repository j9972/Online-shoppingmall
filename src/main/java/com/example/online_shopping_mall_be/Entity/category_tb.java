package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/*
    상품(N) : 카테고리(1)
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class category_tb {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    @Column
    private String category_name; // 카테고리명

    @OneToMany(mappedBy = "category")
    private List<product_tb> products = new ArrayList<>();
}
