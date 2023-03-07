package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/*
    주문 상세 번호(1) : 주문 (1)
    주문상세(1) : 상품(N)
    주문 상세 ( 1 ) : 주문 취소 ( 1 )
 */
@Entity
public class order_detail_tb {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_detail_id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private order_tb order_id;

    @OneToMany(mappedBy = "order_detail_id")
    private List<product_tb> products = new ArrayList<>();

    @Column
    private Integer total_count; // 총 수량

    private Integer total_price; // 총 금액

    private Integer phone_number; // 전화번호

    private String receiver; // 수령인

    private String address; // 주소

    private String order_request; // 주문 요청 사항

    private String order_status;// 처리 상태

    private boolean refund_possibility; // 환불 가능 여부

    @OneToOne(mappedBy = "order_detail_id", fetch = FetchType.LAZY)
    private order_cancel_tb order_cancel;
}
