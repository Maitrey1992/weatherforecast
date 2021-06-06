package com.example.weather.weatherforcast.service;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.weather.weatherforcast.models.WeatherReportInfo;

@Service
public class WeatherReportPublisherService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(WeatherReportPublisherService.class);
	
	@Autowired
	private KafkaProducer<String, WeatherReportInfo> weatherKafkaProducer;
	
	@Value("${weather.kafka-topic}")
	private String weatherDataTopic;
	
	public void publish(WeatherReportInfo weatherInfo) {
		String key = weatherInfo.getName();
		ProducerRecord<String, WeatherReportInfo> producerRecord = new ProducerRecord<String, WeatherReportInfo>(weatherDataTopic, key, weatherInfo);
		LOGGER.info("Publishing message for city : {} ", key);
		weatherKafkaProducer.send(producerRecord, new Callback() {
			
			@Override
			public void onCompletion(RecordMetadata metadata, Exception exception) {
				
				if(exception == null ) {
					LOGGER.info("Message published : \n Topic : {} \n Partition : {} \n timestamp : {}\n",
							metadata.topic(), metadata.partition(), metadata.timestamp());
				}else {
					LOGGER.error("Error while publishing.",exception);
				}
				
			}
		});
		weatherKafkaProducer.flush();
	}

}
