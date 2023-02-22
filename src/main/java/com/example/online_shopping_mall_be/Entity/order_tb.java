package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
    주문(N) : 회원(1)
    주문(1) : 주문 상세 번호 (1)
    주문(1) : 배송지(1) -> 배송지, 주문 상세 번호 ( 겹치는데 어케 처리 )
 */

@Entity
public class order_tb {
    @Id @GeneratedValue
    private Long order_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private user_tb user_id;

    @OneToMany(mappedBy = "order_id")
    private List<product_tb> products = new ArrayList<>();

    @Column
    private Date register_order_date;

    @OneToOne(mappedBy = "order_id", fetch = FetchType.LAZY)
    private delivery_point_tb delivery_point;

    @OneToOne(mappedBy = "order_id", fetch = FetchType.LAZY)
    private order_detail_tb order_detail;
}
