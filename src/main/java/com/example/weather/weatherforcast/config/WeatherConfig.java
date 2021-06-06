package com.example.weather.weatherforcast.config;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.example.weather.weatherforcast.models.WeatherReportInfo;
import com.example.weather.weatherforcast.utils.KafkaJsonSerializer;
import com.fasterxml.jackson.databind.JsonSerializer;

@Configuration
public class WeatherConfig {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public KafkaProducer<String, WeatherReportInfo> weatherKafkaProducer(@Value("${weather.kafka-service}") String bootStrapServer,
			@Value("${weather.kafka-topic}") String kafkaTopic){
		Properties properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaJsonSerializer.class.getName());
		return new KafkaProducer<>(properties);
	}
}
