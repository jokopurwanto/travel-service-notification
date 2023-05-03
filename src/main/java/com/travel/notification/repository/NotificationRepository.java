package com.travel.notification.repository;

import com.travel.notification.model.NotificationModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationModel, Integer> {
}
