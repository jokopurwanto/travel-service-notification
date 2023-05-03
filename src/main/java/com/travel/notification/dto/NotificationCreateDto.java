package com.travel.notification.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Data
@Builder
public class NotificationCreateDto {

    @NotNull(message = "Invalid status: status is NULL")
    private Boolean status;

    @NotBlank(message = "Invalid total email: Empty email")
    @NotNull(message = "Invalid total email: email is NULL")
    private String email;

}
