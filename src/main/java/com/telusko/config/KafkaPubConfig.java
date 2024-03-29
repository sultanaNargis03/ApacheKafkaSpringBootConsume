package com.telusko.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.telusko.model.Customer;
import com.telusko.util.AppConstants;

@Configuration
public class KafkaPubConfig 
{
	@Bean
	public ConsumerFactory<String, Customer> consumerFactory() {

		Map<String, Object> configProps = new HashMap<String, Object>();

		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConstants.HOST_URL);
		System.out.println("URL is fine");
		configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		System.out.println("Json data adding in ProducerFactpry is fine");

		return new DefaultKafkaConsumerFactory<>(configProps);
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Customer> kafkaListenerFactory() {
		
		ConcurrentKafkaListenerContainerFactory<String, Customer> factory=
				new ConcurrentKafkaListenerContainerFactory<String, Customer>();
	factory.setConsumerFactory(consumerFactory());
	return factory;
	}
	
	
}

