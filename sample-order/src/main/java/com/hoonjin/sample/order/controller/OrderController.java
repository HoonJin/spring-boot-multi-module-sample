package com.hoonjin.sample.order.controller;

import com.hoonjin.sample.order.dto.OrderDto;
import com.hoonjin.sample.order.dto.ResponseOrder;
import com.hoonjin.sample.order.entity.Order;
import com.hoonjin.sample.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order-service/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable String userId, @RequestBody OrderDto orderDto) {
        orderDto.setUserId(userId);
        Order order = orderService.createOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseOrder.of(order));
    }

    @GetMapping("/order-service/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrder(@PathVariable String userId) {
        log.info("before retrieve order data");
        List<Order> orders = orderService.getOrdersByUserId(userId);
        List<ResponseOrder> res = orders.stream().map(ResponseOrder::of)
                .collect(Collectors.toList());
        log.info("after retrieve order data");
        return ResponseEntity.ok(res);
    }
}
