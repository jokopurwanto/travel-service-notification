package com.travel.notification.db.notificationdb.repository;

import com.travel.notification.db.notificationdb.model.NotificationModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationModel, Integer> {
}
