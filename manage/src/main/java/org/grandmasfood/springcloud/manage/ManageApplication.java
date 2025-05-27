package org.grandmasfood.springcloud.manage;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;

@SpringBootApplication
public class ManageApplication implements CommandLineRunner {
    public static final Logger log = LoggerFactory.getLogger(ManageApplication.class);
    private final KafkaTemplate kafkaTemplate;

    public ManageApplication(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "devs4j-topic", containerFactory = "kafkaListenerContainerFactory", groupId = "devs4j-group", properties = {"max.poll.interval.ms:4000", "max.poll.records:10"})
    public void listen(List<ConsumerRecord<String, String>> messages) {
        for (ConsumerRecord<String, String> message : messages) {
            log.info("Partition = {}, key = {}, Offset = {}, Value = {}", message.partition(), message.key(), message.offset(), message.value());
        }
    }


    public static void main(String[] args) {
        SpringApplication.run(ManageApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 100; i++) {

            kafkaTemplate.send("devs4j-topic", String.format("Sample message %d ", i));
        }

    }

}
