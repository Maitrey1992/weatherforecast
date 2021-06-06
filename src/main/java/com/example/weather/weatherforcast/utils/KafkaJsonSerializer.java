package com.example.weather.weatherforcast.utils;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KafkaJsonSerializer implements Serializer<Object> {

	@Override
	public byte[] serialize(String topic, Object data) {

		byte[] byteValue = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			 byteValue = mapper.writeValueAsBytes(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return byteValue;
	}

}
