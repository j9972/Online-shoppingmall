package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;

/*
    주문 상세 ( 1 ) : 주문 취소 ( 1 )
 */
@Entity
public class order_cancel_tb {
    @Id @GeneratedValue
    private Long refund_id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "product_detail_id")
    private order_detail_tb product_detail_id;

    @Column
    private String refund_reason; // 환불 사유

    private String refund_bank_name; // 환불 계좌

    private Integer refund_bank_number; // 환불 계좌 번호

    @Lob
    private String refund_photo; // 환불 사유 사진
}
