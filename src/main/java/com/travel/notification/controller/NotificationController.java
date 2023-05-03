package com.travel.notification.controller;

import com.travel.notification.model.NotificationModel;
import com.travel.notification.service.Imple.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api")
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @GetMapping("/notification")
    public List<NotificationModel> list(){
        return notificationService.getAllNotification();
    }

    @GetMapping("/notification/{id}")
    public ResponseEntity<NotificationModel> get(@PathVariable Integer id){
        try {
            NotificationModel notificationModel = notificationService.get(id);
            return new ResponseEntity<NotificationModel>(notificationModel, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<NotificationModel>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/notification")
    public void add(@RequestBody NotificationModel notificationModel){
        notificationService.save(notificationModel);
    }

    @PutMapping("/notification/{id}")
    public ResponseEntity<?> update(@RequestBody NotificationModel notificationModel, @PathVariable Integer id){
        try {
            NotificationModel existCatalog = notificationService.get(id);
            notificationService.save(notificationModel);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/notification/{id}")
    public void delete(@PathVariable Integer id) {
        notificationService.delete(id);
    }

}
