package com.travel.notification.handler;

import com.travel.notification.dto.NotificationExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotificationExceptionHandler {
    @ExceptionHandler(value = {NotificationNotFoundException.class})
    public ResponseEntity<Object> handleCatalogNotFoundException (NotificationNotFoundException notificationNotFoundException){
        NotificationExceptionDto notificationExceptionDto = new NotificationExceptionDto(
                HttpStatus.NOT_FOUND.value(),
                notificationNotFoundException.getMessage(),
                notificationNotFoundException.getCause()

        );
        return new ResponseEntity<>(notificationExceptionDto, HttpStatus.NOT_FOUND);
    }
}
