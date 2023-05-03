package com.travel.notification.service;

import com.travel.notification.model.NotificationModel;

import java.util.List;

public interface INotificationService {
    public List<NotificationModel> getAllNotification();
    public NotificationModel get(Integer id);
    public void save(NotificationModel notificationModel);
    public void delete(Integer id);
}
