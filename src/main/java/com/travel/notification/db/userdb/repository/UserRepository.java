package com.travel.notification.db.userdb.repository;

import com.travel.notification.db.userdb.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
}
