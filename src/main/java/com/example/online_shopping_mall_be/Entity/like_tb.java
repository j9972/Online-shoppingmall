package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;
import lombok.*;

/*
    게시글 ( 1 ) : 좋아요 ( n )
    좋아요 ( n ) : 댓글 ( 1 )
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class like_tb {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long like_id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private post_tb post; // FK - 게시글 아이디

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private comment_tb comment; // FK - 게시글 아이디
}
