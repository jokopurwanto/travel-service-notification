package com.travel.notification.service.Imple;

import com.travel.notification.model.NotificationModel;
import com.travel.notification.repository.NotificationRepository;
import com.travel.notification.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public List<NotificationModel> getAllNotification() {
        System.out.println(notificationRepository.findAll());
        return notificationRepository.findAll();
    }

    @Override
    public NotificationModel get(Integer id){
        return notificationRepository.findById(id).get();
    }

    @Override
    public void save(NotificationModel notificationModel){
        notificationRepository.save(notificationModel);
    }

    @Override
    public void delete(Integer id){
        notificationRepository.deleteById(id);
    }
}
