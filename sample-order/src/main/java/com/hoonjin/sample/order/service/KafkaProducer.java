package com.hoonjin.sample.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoonjin.sample.order.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, OrderDto orderDto) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String jsonInString = objectMapper.writeValueAsString(orderDto);
            kafkaTemplate.send(topic, jsonInString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
