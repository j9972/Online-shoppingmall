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
    private String receiver_name; // 수취인 이름

    private String address; // 수취인 주소

    private String delivery_status; // 배송 상태

    private Integer receiver_phone_num; // 수취인 번호

    private Integer zip_code; // 우편 번호

    private Date start_delivery_date; // 배송 시작 날짜

    private Date end_delivery_date;// 배송 완료 날짜

    @OneToOne(mappedBy = "delivery_point_id", fetch = FetchType.LAZY)
    private waybill_tb waybill;
}
