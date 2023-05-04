package com.travel.notification.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@Data
@Builder
public class NotificationCreateDto {

    @NotNull(message = "Invalid idUser: idUser is NULL")
    private Integer idUser;

    @NotNull(message = "Invalid idOrder: idOrder is NULL")
    private Integer idOrder;

    @NotNull(message = "Invalid destination: destination is NULL")
    private String destination;

    @NotNull(message = "Invalid startDate: startDate is NULL")
    private Date startDate;

    @NotNull(message = "Invalid endDate: endDate is NULL")
    private Date endDate;

    @NotNull(message = "Invalid totalPerson: totalPerson is NULL")
    private Integer totalPerson;

    @NotNull(message = "Invalid totalPrice: totalPrice is NULL")
    private String totalPrice;
}
