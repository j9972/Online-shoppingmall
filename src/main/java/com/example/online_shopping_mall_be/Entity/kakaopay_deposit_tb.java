package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;

import java.util.Date;

/*
    카카오 입금(1) : 결제 방식(1)
 */
@Entity
public class kakaopay_deposit_tb {
    @Id @GeneratedValue
    private Long kakaopay_deposit_id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="payment_method_id")
    private payment_method_tb payment_method_id;

    @Column
    private Integer order_price; // 주문 금액

    private Integer deposit_price; // 입금 금액

    private Date deposit_date; // 입금 날짜

    private Date payment_expire_period; // 결제 유효기간

    private boolean payment_status; // 결제 상태
}
