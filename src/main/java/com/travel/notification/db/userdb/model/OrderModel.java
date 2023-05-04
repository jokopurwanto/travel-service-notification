package com.travel.notification.db.userdb.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@Entity
@Table(name = "tbl_orders")
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "id_payment")
    private Integer idPayment;

    @Column(name = "id_notification")
    private Integer idNotification;

    @Column(name = "date")
    private Date date;

    @Column(name = "start_order_date")
    private Date startDate;

    @Column(name = "end_order_date")
    private Date endDate;

    @Column(name = "num_of_orders")
    private Integer totalPerson;

}
