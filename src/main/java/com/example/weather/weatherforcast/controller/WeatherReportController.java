package com.example.weather.weatherforcast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.weather.weatherforcast.models.WeatherReportInfo;
import com.example.weather.weatherforcast.service.WeatherReportService;

@RestController
public class WeatherReportController {
	
	@Autowired
	private WeatherReportService weatherReportService;

	@GetMapping("/getWatherByZipcode")
	public ResponseEntity<WeatherReportInfo> getWeather(@RequestParam(value = "zipcode" , defaultValue = "380061") String zipCode) {
		WeatherReportInfo response = weatherReportService.getWeatherReportByZipCode(zipCode); 
		return ResponseEntity.ok(response);
	}
	
}
