package com.hoonjin.sample.order.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class OrderDto {

    private String productId;

    private Integer qty;

    private Integer unitPrice;

    @Setter
    private String userId;
}
