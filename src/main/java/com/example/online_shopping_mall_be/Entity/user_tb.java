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
    private String user_name;

    private Integer user_age;

    private Integer user_phone;

    private Integer refund_bank_account;

    private String user_address;

    private String refund_bank;

    private Date register_date;

    private Date update_userInfo_date;

    @OneToMany(mappedBy = "user_id")
    private List<post_tb> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user_id")
    private List<comment_tb> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user_id")
    private List<order_tb> orders = new ArrayList<>();

    @OneToMany(mappedBy = "user_id")
    private List<ask_tb> asks = new ArrayList<>();

    @OneToMany(mappedBy = "review_id")
    private List<review_tb> reviews = new ArrayList<>();

    @OneToOne(mappedBy = "user_id", fetch = FetchType.LAZY)
    private user_tb user;
}
