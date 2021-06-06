package com.example.weather.weatherforcast.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.weather.weatherforcast.models.WeatherReportInfo;

@Service
public class WeatherReportService {
	
	private final String API_ID = "6443b8305b48cb5bc403bb0819931c49";
	
	private final String WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/weather?";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WeatherReportPublisherService weatherReportPublisherService;
	
	public WeatherReportInfo getWeatherReportByZipCode(String zipCode) {
		
		StringBuffer fullApiUrl = new StringBuffer(WEATHER_API_URL);
		fullApiUrl.append("zip="+zipCode);
		fullApiUrl.append("&appid=");
		fullApiUrl.append(API_ID);
		
		WeatherReportInfo response = restTemplate.getForObject(fullApiUrl.toString(), WeatherReportInfo.class);
		
		weatherReportPublisherService.publish(response);
		return response;
	}
}
