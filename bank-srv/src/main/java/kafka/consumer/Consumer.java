package kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;

public class Consumer {

    @KafkaListener(topics = "user")
    public void consume(String massage){

    }
}
