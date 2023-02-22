package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;

import java.util.Date;
/*
    주문(1) : 배송지(1)
    배송지(1) : 운송장(1)
 */

@Entity
public class delivery_point_tb {
    @Id @GeneratedValue
    private Long delivery_point_id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "order_id")
    private order_tb order_id;

    @Column
    private String receiver_name;

    private String address;

    private String delivery_status;

    private Integer receiver_phone_num;

    private Integer zip_code;

    private Date start_delivery_date;

    private Date end_delivery_date;

    @OneToOne(mappedBy = "delivery_point_id", fetch = FetchType.LAZY)
    private waybill_tb waybill;
}
