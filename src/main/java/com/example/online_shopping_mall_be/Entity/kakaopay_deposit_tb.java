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
    private Integer order_price;

    private Integer deposit_price;

    private Date deposit_date;

    private Date payment_expire_period;

    private boolean payment_status;
}
