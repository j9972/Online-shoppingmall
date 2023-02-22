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
    private String refund_reason;

    private String refund_bank_name;

    private Integer refund_bank_number;

    @Lob
    private String refund_photo;
}
