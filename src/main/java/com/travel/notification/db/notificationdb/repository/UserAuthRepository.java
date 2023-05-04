package com.travel.notification.db.notificationdb.repository;

import com.travel.notification.db.notificationdb.model.UserAuthModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<UserAuthModel, Integer> {
    UserAuthModel findByUsername(String username);
}