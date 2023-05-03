package com.travel.notification.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@Entity
@Table(name = "tbl_notification")
@AllArgsConstructor
@NoArgsConstructor
public class NotificationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "email")
    private String email;

    @Column(name = "createdAt")
    private Timestamp cretedAt;

}
