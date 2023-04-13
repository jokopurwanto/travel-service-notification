package com.travel.notification.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "tbl_notification")
public class NotificationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "content")
    private String content;

    @Column(name = "email")
    private String email;

    @Column(name = "createdAt")
    private Timestamp cretedAt;



}
