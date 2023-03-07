package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;

import java.util.Date;

/*
    배송지(1) : 운송장(1)
 */
@Entity
public class waybill_tb {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long waybill_id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "delivery_point_id")
    private delivery_point_tb delivery_point_id;

    @Column
    private String delivery_company; // 택배사

    private Integer waybill_number; // 운송장 번호

    private Date waybill_register_date; // 운송장 등록일

    private Date waybill_update_date; // 운송장 수정일
}
