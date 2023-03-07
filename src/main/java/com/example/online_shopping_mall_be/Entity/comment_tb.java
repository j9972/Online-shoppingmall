package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;

import java.util.Date;

/*
    댓글(N) : 게시글(1)
    댓글(N) : 회원(1)
 */
@Entity
public class comment_tb {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private user_tb user_id;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private post_tb post_id;

    @Column
    private String comment_body; // 댓글 내용

    private Date comment_register_date; // 댓글 업로드 일정

    private Date comment_update_date; // 댓글 수정 일정
}
