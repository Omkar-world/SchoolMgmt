package com.school.subscriber;

import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Subscriber {

	@KafkaListener(topics = "${app.topic.name}", groupId = "school-group")
	public void consumeMessage(String message) {
		log.info("Received message from Kafka topic: {}", message);
	}
}
