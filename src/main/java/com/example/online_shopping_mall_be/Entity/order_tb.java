package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
    주문(N) : 회원(1)
    주문(1) : 주문 상세 번호 (1)
    주문(1) : 배송지(1) -> 배송지, 주문 상세 번호 ( 겹치는데 어케 처리 )
 */

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class order_tb {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private user_tb user;

    @Column
    private Date register_order_date; // 주문 등록 날짜

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "delivery_point_id")
    private delivery_point_tb delivery_point;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_detail_id")
    private order_detail_tb order_detail;
}
