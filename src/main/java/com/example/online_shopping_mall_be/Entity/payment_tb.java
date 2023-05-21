package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/*
    결제가 완료된 상태에서 주어짐
    결제(1)(외래키) : 상품(n)
    결제(N) : 결제 방식(1)
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class payment_tb {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payment_id;

    @OneToMany(mappedBy = "payment")
    private List<product_tb> products = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id")
    private payment_method_tb payment_method_id;

    @Column
    private String payment_naver; // 네이버 페이

    private String payment_kakao; // 카카오 페이

    private String payment_bank; // 무통장 입금
}
