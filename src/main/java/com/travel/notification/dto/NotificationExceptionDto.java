package com.travel.notification.dto;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
public class NotificationExceptionDto {
    private final Integer status;
    private final String message;
    private final Throwable throwable;
}
