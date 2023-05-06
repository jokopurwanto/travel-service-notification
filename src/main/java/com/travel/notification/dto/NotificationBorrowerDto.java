package com.travel.notification.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Data
@Builder
public class NotificationBorrowerDto {

    @NotNull(message = "Invalid username: username is NULL")
    private String username;

    @NotNull(message = "Invalid totalPayment: totalPayment is NULL")
    private String totalPayment;

    @NotNull(message = "Invalid product: product is NULL")
    private String product;

    @NotNull(message = "Invalid email: email is NULL")
    private String email;

}
