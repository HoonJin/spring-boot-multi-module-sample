package com.hoonjin.sample.catalog.domain;

import com.hoonjin.sample.catalog.entity.Catalog;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class CatalogDto {

    private String productId;

    private Integer qty;

    private Integer unitPrice;

    private Integer totalPrice;

    private String orderId;

    private String userId;

    public static CatalogDto of(Catalog obj) {
        CatalogDto dto = new CatalogDto();
        return dto;
    }
}
