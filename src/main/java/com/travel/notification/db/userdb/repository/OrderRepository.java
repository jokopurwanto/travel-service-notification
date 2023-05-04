package com.travel.notification.db.userdb.repository;

import com.travel.notification.db.userdb.model.OrderModel;
import com.travel.notification.db.userdb.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, Integer> {
}
