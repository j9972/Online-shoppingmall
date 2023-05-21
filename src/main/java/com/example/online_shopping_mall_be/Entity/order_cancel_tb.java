package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;
import lombok.*;
//import org.jetbrains.annotations.NotNull;

/*
    주문 상세 ( 1 ) : 주문 취소 ( 1 )
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class order_cancel_tb {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refund_id;

    @OneToOne(mappedBy = "order_cancel", fetch = FetchType.LAZY)
    private order_detail_tb order_detail_id;

    @Column
    private String refund_reason; // 환불 사유

    //@NotNull
    private String refund_bank_name; // 환불 계좌

    //@NotNull
    private Integer refund_bank_number; // 환불 계좌 번호

    @Lob
    private String refund_photo; // 환불 사유 사진
}
