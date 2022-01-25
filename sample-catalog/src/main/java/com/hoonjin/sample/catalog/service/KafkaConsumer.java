package com.hoonjin.sample.catalog.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoonjin.sample.catalog.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final CatalogRepository catalogRepository;

    @KafkaListener(topics = "example-catalog-topic", groupId = "test")
    public void updateQty(String msg) {
        log.info("KAFKA MSG: {}", msg);


        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<Object, Object> msgMap = objectMapper.readValue(msg, new TypeReference<Map<Object, Object>>() {});
            catalogRepository.findByProductId((String) msgMap.get("productId"))
                    .map(catalog -> {
                        catalog.setStock(catalog.getStock() - (Integer) msgMap.get("qty"));
                        return catalogRepository.save(catalog);
                    })
                    .orElseThrow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
