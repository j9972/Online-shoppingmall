package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;

import java.util.Date;

/*
    무통장 입금(1) : 결제 방식(1)
 */
@Entity
public class bank_deposit_tb {
    @Id @GeneratedValue
    private Long bank_deposit_id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="payment_method_id")
    private payment_method_tb payment_method_id;

    @Column
    private String bank_name;

    private Integer bank_code;

    private Integer order_price;

    private Integer deposit_price;

    private Date deposit_date;

    private Date payment_expire_period;

    private boolean payment_status;
}

