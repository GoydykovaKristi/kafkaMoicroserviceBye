package com.goydykova.microservice.kafka.bye.kafkaMoicroserviceBye.RESTController;

import com.goydykova.microservice.kafka.bye.kafkaMoicroserviceBye.service.ByeService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableKafka
@RequestMapping("bye")
public class ByeController {

    @Autowired
    private KafkaTemplate<String, Long> kafkaTemplate;
    @Autowired
    private ByeService byeService;


    @GetMapping("")
    public String byePage() throws InterruptedException{
        kafkaTemplate.send("bye", 1L);
        if (byeService.getHelloNumb() == -2) {
            return "Что-то пошло не так =( (NO CONNECTION TO THE DATABASE)";
        }
        return "Всего доброго! Вы здоровались " + byeService.getHelloNumb() + " раз.";
    }
}
