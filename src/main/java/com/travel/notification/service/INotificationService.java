package com.travel.notification.service;

import com.travel.notification.db.userdb.model.UserModel;
import com.travel.notification.dto.NotificationCreateDto;
import com.travel.notification.dto.NotificationUpdateDto;
import com.travel.notification.db.notificationdb.model.NotificationModel;

import java.util.List;
import java.util.Map;

public interface INotificationService {

    public NotificationModel createNotification(NotificationCreateDto notificationCreateDto);
    public NotificationModel updateNotification(NotificationUpdateDto notificationUpdateDto, Integer id);
    public NotificationModel getNotificationByID(Integer id);
    public List<NotificationModel> getAllNotification();
    public Map<String, Object> deleteNotification(Integer id);
    public List<UserModel> getAllUser();
    public NotificationModel get(Integer id);
    public void save(NotificationModel notificationModel);
    public void delete(Integer id);
}
