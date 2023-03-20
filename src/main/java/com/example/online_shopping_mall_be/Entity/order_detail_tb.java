package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/*
    주문 상세 번호(1) : 주문 (1)
    주문상세(1) : 상품(N)
    주문 상세 ( 1 ) : 주문 취소 ( 1 )
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class order_detail_tb {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_detail_id;

    @OneToOne(mappedBy = "order_detail", fetch = FetchType.LAZY)
    private order_tb order_id;

    @OneToMany(mappedBy = "order_detail")
    private List<product_tb> products = new ArrayList<>();

    @Column
    @NotNull
    private Integer total_count; // 총 수량

    @NotNull
    private Integer total_price; // 총 금액

    @NotNull
    private Integer phone_number; // 전화번호

    @NotNull
    private String receiver; // 수령인

    @NotNull
    private String address; // 주소

    private String order_request; // 주문 요청 사항

    private String order_status;// 처리 상태

    private boolean refund_possibility; // 환불 가능 여부


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "order_cancel_id")
    private order_cancel_tb order_cancel;
}
