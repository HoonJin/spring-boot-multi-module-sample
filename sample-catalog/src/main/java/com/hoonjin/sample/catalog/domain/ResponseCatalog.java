package com.hoonjin.sample.catalog.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hoonjin.sample.catalog.entity.Catalog;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Setter(AccessLevel.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCatalog {

    private String productId;

    private String productName;

    private Integer unitPrice;

    private Integer stock;

    private LocalDateTime createdAt;

    public static ResponseCatalog of(Catalog obj) {
        ResponseCatalog res = new ResponseCatalog();
        res.productId = obj.getProductId();
        res.productName = obj.getProductName();
        res.unitPrice = obj.getUnit();
        res.stock = obj.getStock();
        res.createdAt = obj.getCreatedAt();
        return res;
    }
}
