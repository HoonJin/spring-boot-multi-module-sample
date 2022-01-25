package com.hoonjin.sample.order.service;

import com.hoonjin.sample.order.dto.OrderDto;
import com.hoonjin.sample.order.entity.Order;
import com.hoonjin.sample.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaProducer kafkaProducer;

    public Order createOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setTotalPrice(orderDto.getQty() * orderDto.getUnitPrice());
        order.setQty(orderDto.getQty());
        order.setProductId(orderDto.getProductId());
        order.setUnitPrice(orderDto.getUnitPrice());
        order.setUserId(orderDto.getUserId());

        kafkaProducer.send("example-catalog-topic", orderDto);

        return orderRepository.save(order);
    }

    public Order getOrdersByOrderId(String orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    public List<Order> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }
}
