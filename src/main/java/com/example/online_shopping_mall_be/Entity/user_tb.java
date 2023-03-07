package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
    회원(1) : 게시글(N)
    회원(1) : 댓글(N)
    주문(N) : 회원(1)
    장바구니(1) : 회원(1)
    회원(1) : 회원 인증(1)
    문의(N) : 회원(1)
    리뷰(n) : 상품(1)
 */
@Entity
public class user_tb {

    @Id @GeneratedValue
    private Long user_id;

    @Column
    private String user_name; // 유저 이름

    private Integer user_age; // 나이

    private Integer user_phone; // 핸드폰 번호

    private Integer refund_bank_account; // 환불 계좌 번호

    private String user_address; // 주소

    private String refund_bank; // 환불 계좌

    private Date register_date; // 회원 가입 일자

    private Date update_userInfo_date; // 회원 정보 수정 일자

    @OneToMany(mappedBy = "user_id")
    private List<post_tb> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user_id")
    private List<comment_tb> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user_id")
    private List<order_tb> orders = new ArrayList<>();

    @OneToMany(mappedBy = "user_id")
    private List<ask_tb> asks = new ArrayList<>();

    @OneToMany(mappedBy = "user_id")
    private List<review_tb> reviews = new ArrayList<>();

    @OneToOne(mappedBy = "user_id", fetch = FetchType.LAZY)
    private shopping_basket_tb shopping_basket;

    @OneToOne(mappedBy = "user_id", fetch = FetchType.LAZY)
    private user_auth_id_tb user_auth;
}
