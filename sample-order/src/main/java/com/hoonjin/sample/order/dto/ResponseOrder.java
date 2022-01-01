package com.hoonjin.sample.order.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hoonjin.sample.order.entity.Order;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Setter(AccessLevel.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseOrder {

    private String productId;

    private Integer unitPrice;

    private Integer stock;

    private LocalDateTime createdAt;

    private String orderId;

    public static ResponseOrder of(Order obj) {
        ResponseOrder res = new ResponseOrder();
        res.productId = obj.getProductId();
        res.unitPrice = obj.getUnitPrice();
        res.stock = obj.getQty();
        res.createdAt = obj.getCreatedAt();
        res.orderId = obj.getOrderId();
        return res;
    }
}
