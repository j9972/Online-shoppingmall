package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/*
    결제(N) : 결제 방식(1)
    결제 방식(1) : 무통장 입금 (1)
    결제 방식(1) : 카카오페이 입금 (1)
    결제 방식(1) : 네이버페이 입금 (1)
 */
@Entity
public class payment_method_tb {
    @Id @GeneratedValue
    private Long payment_method_id;

    @Column
    private String payment_method;

    @OneToMany(mappedBy = "payment_method_id")
    private List<payment_method_tb> payment_methods = new ArrayList<>();

    @OneToOne(mappedBy = "payment_method_id", fetch = FetchType.LAZY)
    private kakaopay_deposit_tb kakaopay_deposit;

    @OneToOne(mappedBy = "payment_method_id", fetch = FetchType.LAZY)
    private bank_deposit_tb bank_deposit;

    @OneToOne(mappedBy = "payment_method_id", fetch = FetchType.LAZY)
    private naverpay_deposit_tb naverpay_deposit;
}
