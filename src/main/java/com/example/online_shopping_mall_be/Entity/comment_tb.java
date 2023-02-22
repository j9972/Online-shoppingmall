package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;

import java.util.Date;

/*
    댓글(N) : 게시글(1)
    댓글(N) : 회원(1)
 */
@Entity
public class comment_tb {

    @Id @GeneratedValue
    private Long comment_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private user_tb user_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private post_tb post_id;

    @Column
    private String comment_body;

    private Date comment_register_date;

    private Date comment_update_date;
}
