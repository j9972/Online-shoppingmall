package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
}
