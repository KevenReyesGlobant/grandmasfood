package org.grandmasfood.springcloud.manage;

import io.micrometer.core.instrument.MeterRegistry;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@SpringBootApplication
@EnableScheduling
public class ManageApplication implements CommandLineRunner {
    public static final Logger log = LoggerFactory.getLogger(ManageApplication.class);
    private final KafkaTemplate kafkaTemplate;
    private final MeterRegistry meterRegistry;
    private final KafkaListenerEndpointRegistry kafkaRegistry;


    public ManageApplication(KafkaTemplate kafkaTemplate, MeterRegistry meterRegistry, KafkaListenerEndpointRegistry kafkaRegistry) {
        this.kafkaTemplate = kafkaTemplate;
        this.meterRegistry = meterRegistry;
        this.kafkaRegistry = kafkaRegistry;
    }

    @KafkaListener(id = "devs4jId", autoStartup = "false", topics = "devs4j-topic", containerFactory = "kafkaListenerContainerFactory", groupId = "devs4j-group", properties = {"max.poll.interval.ms:4000", "max.poll.records:10"})
    public void listen(List<ConsumerRecord<String, String>> messages) {
        for (ConsumerRecord<String, String> message : messages) {
            log.info("Partition = {}, key = {}, Offset = {}, Value = {}", message.partition(), message.key(), message.offset(), message.value());
        }
    }


    public static void main(String[] args) {
        SpringApplication.run(ManageApplication.class, args);
    }

    @Scheduled(fixedDelay = 2000, initialDelay = 500)
    public void messageCountMetric() {
        double count = meterRegistry.get("kafka.producer.record.send.total")
                .functionCounter().count();
        log.info("Count {} ",count);
    }


//    @Scheduled(fixedDelay = 1000, initialDelay = 100)
//    public void kafkaSendMessages() {
//        for (int i = 0; i < 100; i++) {
//            kafkaTemplate.send("devs4j-topic", String.valueOf(i), String.format("sample message", i));
//            log.info("Devs4j Rules");
//        }
//    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 100; i++) {
            kafkaTemplate.send("devs4j-topic", String.format("Sample message %d ", i));
        }
        Thread.sleep(5000);
        kafkaRegistry.getListenerContainer("devs4jId").start();


    }

}
