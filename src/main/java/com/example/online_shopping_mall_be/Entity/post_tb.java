package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;

/*
    회원(1) : 게시글(N)
 */
import java.util.Date;

@Entity
public class post_tb {

    @Id @GeneratedValue
    private Long post_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private user_tb user_id;

    private String post_title;

    private String post_body;

    private Date post_register_date;

    private Date post_update_date;

    private boolean comment_status;
}
