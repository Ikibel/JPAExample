package com.example.RentCarTry;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


public class CarRentalClient {
	
	private static final Logger log = LoggerFactory.getLogger(CarRentalClient.class);

	public static void main(String[] args) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<Car> request = new HttpEntity<>(new Car("44QQ66", "Peugeot" , 1200, 5));
		restTemplate.postForObject("http://localhost:8080/cars", request, Car.class);
		
        List cars = restTemplate.getForObject("http://localhost:8000/cars", List.class);
        log.info(cars.toString());
        
	}

}