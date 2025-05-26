package org.grandmasfood.springcloud.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class ManageApplication {

    @KafkaListener(topics = "devs4j-topic", groupId = "devs4j-group")
    public void listen(String message) {
        System.out.println("Received Messasge in group foo: " + message);
    }

    public static void main(String[] args) {
        SpringApplication.run(ManageApplication.class, args);
    }

}
