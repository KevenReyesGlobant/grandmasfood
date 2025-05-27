package org.grandmasfood.springcloud.manage;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class ManageApplication implements CommandLineRunner {
    private final KafkaTemplate kafkaTemplate;

    public ManageApplication(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "devs4j-topic", groupId = "devs4j-group")
    public void listen(String message) {
        System.out.println("Received Messasge in group foo: " + message);
    }

    public static void main(String[] args) {
        SpringApplication.run(ManageApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        kafkaTemplate.send("devs4j-topic", "sample message");
    }
}
