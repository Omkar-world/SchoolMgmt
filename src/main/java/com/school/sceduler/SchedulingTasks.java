package com.school.sceduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.school.subscriber.Subscriber;

@Configuration
@EnableScheduling
public class SchedulingTasks {
	@Autowired
	private Subscriber subscriber;

	@Scheduled(cron = "* * 9 * * *")
	public void performTask() {
		subscriber.consumeMessage("school-topic");
	}

}
