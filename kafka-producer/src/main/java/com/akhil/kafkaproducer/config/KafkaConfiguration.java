package com.akhil.kafkaproducer.config;

import java.util.HashMap;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.akhil.kafkaproducer.model.Product;

@Configuration
public class KafkaConfiguration {
	
	@Value("${kafka.host}")
	private String host;
	
	@Bean
	public ProducerFactory<String, Product> producerFactory() {
		
		HashMap<String, Object> config = new HashMap<>();
		
		
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, host);
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		
		return new DefaultKafkaProducerFactory<>(config);
		
	}
	
	@Bean
	public KafkaTemplate<String, Product> getKafkaTemplate(){
		return new KafkaTemplate<>(producerFactory());
		
	}

}
