package com.hoonjin.sample.order.repository;

import com.hoonjin.sample.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByOrderId(String orderId);

    List<Order> findByUserId(String userId);
}
