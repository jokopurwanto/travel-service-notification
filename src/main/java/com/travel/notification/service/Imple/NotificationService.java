package com.travel.notification.service.Imple;

import com.travel.notification.dto.NotificationCreateDto;
import com.travel.notification.dto.NotificationUpdateDto;
import com.travel.notification.handler.NotificationNotFoundException;
import com.travel.notification.model.NotificationModel;
import com.travel.notification.repository.NotificationRepository;
import com.travel.notification.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.ZonedDateTime;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public NotificationModel createNotification(NotificationCreateDto notificationCreateDto) {
        ZonedDateTime now = ZonedDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now.toLocalDateTime());
        NotificationModel notificationModel = NotificationModel.builder()
                .status(notificationCreateDto.getStatus())
                .email(notificationCreateDto.getEmail())
                .cretedAt(timestamp)
                .build();
        return notificationRepository.save(notificationModel);
    }

    @Override
    public NotificationModel updateNotification(NotificationUpdateDto notificationUpdateDto, Integer id){
        if(notificationRepository.findById(id).isEmpty())
            throw new NotificationNotFoundException("Data notification yang akan di-update tidak ditemukan");

        NotificationModel notificationModel = NotificationModel.builder()
                .id(id)
                .status(notificationUpdateDto.getStatus())
                .email(notificationUpdateDto.getEmail())
                .cretedAt(notificationUpdateDto.getCretedAt())
                .build();
        notificationRepository.save(notificationModel);
        return notificationRepository.findById(id).get();
    }

    @Override
    public List<NotificationModel> getAllNotification() {
        System.out.println(notificationRepository.findAll());
        return notificationRepository.findAll();
    }

    @Override
    public Map<String, Object> deleteNotification(Integer id) {
        if(notificationRepository.findById(id).isEmpty())
            throw new NotificationNotFoundException("Data yang dicari tidak ditemukan");

        NotificationModel notificationModel = notificationRepository.findById(id).get();
        Map<String,Object> response = new HashMap<>();
        response.put("id", notificationModel.getId());
        response.put("status", notificationModel.getStatus());
        response.put("email", notificationModel.getEmail());
        response.put("cretedAt",notificationModel.getCretedAt());
        notificationRepository.deleteById(id);
        return response;
    }



    @Override
    public NotificationModel getNotificationByID(Integer id) {
        if(notificationRepository.findById(id).isEmpty())
            throw new NotificationNotFoundException("Data yang dicari tidak ditemukan");
        return notificationRepository.findById(id).get();
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
