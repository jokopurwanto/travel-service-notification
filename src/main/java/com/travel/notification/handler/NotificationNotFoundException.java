package com.travel.notification.handler;

public class NotificationNotFoundException extends RuntimeException{
    public NotificationNotFoundException(String message){
        super(message);
    }

    public NotificationNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
