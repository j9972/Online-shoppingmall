package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/*
    주문 상세 번호(1) : 주문 번호 (1)
    주문상세(1) : 상품(N)
    주문 상세 ( 1 ) : 주문 취소 ( 1 )
 */
@Entity
public class order_detail_tb {
    @Id @GeneratedValue
    private Long order_detail_id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private order_tb order_id;

    @OneToMany(mappedBy = "order_detail_id")
    private List<product_tb> products = new ArrayList<>();

    @Column
    private Integer total_count;

    private Integer total_price;

    private Integer phone_number;

    private String receiver;

    private String address;

    private String order_request;

    private String order_status;

    private boolean refund_possibility;

    @OneToOne(mappedBy = "order_detail_id", fetch = FetchType.LAZY)
    private order_detail_tb order_detail;
}
