package com.school.prducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

	@Autowired
	private  KafkaTemplate<String, String> kafkaTemplate;

	@Value("${app.topic.name}")
	private String topicName;
	

	public void sendMessage(String topic, String message) {
		kafkaTemplate.send(topicName, message);
	}
}
