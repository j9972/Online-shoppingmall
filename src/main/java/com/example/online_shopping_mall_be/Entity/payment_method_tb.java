package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/*
    결제(N) : 결제 방식(1)


 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class payment_method_tb {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payment_method_id;

    @Column
    private String payment_method; // 결제 방법

    // 결제 - 결제방식 : 다대일 단방향이라서 지움
//    @OneToMany(mappedBy = "payment_method_id")
//    private List<payment_method_tb> payments = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="kakaopay_deposit_id")
    private kakaopay_deposit_tb kakaopay_deposit;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="bank_deposit_id")
    private bank_deposit_tb bank_deposit;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="naverpay_deposit_id")
    private naverpay_deposit_tb naverpay_deposit;
}
