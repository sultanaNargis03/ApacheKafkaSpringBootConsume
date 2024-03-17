package com.telusko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import com.telusko.util.AppConstants;

@SpringBootApplication
public class ApacheKafkaSpringBootApplication 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(ApacheKafkaSpringBootApplication.class, args);
	}

	@KafkaListener(topics = AppConstants.TOPIC_NAME, groupId="group_telusko_customer")
	public void subscribeMsg(String cxData) 
	{
			System.out.print("* Msg Recieved From Kafka server* :: ");
			System.out.println(cxData);
	
	}
}
