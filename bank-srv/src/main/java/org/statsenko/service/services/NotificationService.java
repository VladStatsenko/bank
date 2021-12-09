package org.statsenko.service.services;

import dto.request.NotificationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final KafkaTemplate<Long, NotificationDto> kafkaTemplate;

    @Value(value = "${kafka.topic.to_analytic}")
    private String greetingTopicName;

    public void send(NotificationDto dto) {
        log.info("<= sending {}", dto);
            kafkaTemplate.send(greetingTopicName, dto);
    }


    @KafkaListener(topics = "${kafka.topic.to_analytic}", containerFactory = "singleFactory")
    public void consume(NotificationDto dto) {
        log.info("=> consumed {}", dto);
    }

}
