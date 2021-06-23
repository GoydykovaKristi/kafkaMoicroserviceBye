package com.goydykova.microservice.kafka.bye.kafkaMoicroserviceBye.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
public class ByeService {
    @Autowired
    KafkaTemplate<String, Long> kafka;

    private long startHelloNumber = -1;

    @KafkaListener(topics = "hello")
    public void setHelloNumb(ConsumerRecord<String, Long> record) {
        this.startHelloNumber = record.offset() + 1;
        kafka.send("countBreakpointHello", "hello", startHelloNumber);
    }

//    public void setHelloNumb(long startHelloNumber) {
//        this.startHelloNumber = startHelloNumber;
//    }

    public long getHelloNumb() throws InterruptedException {
        while (startHelloNumber == -1) {
            kafka.send("givHello",  2L);
            Thread.sleep(1000);
            if (startHelloNumber == -1) {
                startHelloNumber = -2L;
            }
        }
        return startHelloNumber;
    }

    @KafkaListener(topics = "returnHello")
    public void helper(Long startHelloNumber) {
        this.startHelloNumber = startHelloNumber;
    }
}
