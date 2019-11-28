package com.example.RentCarTry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestWebService {

	CarRepository carRepository;
	
	@Autowired
	public RestWebService(CarRepository carRepository) {
		super();
		this.carRepository = carRepository;
	}
	
	@GetMapping("/vehicules")
	public Iterable<Car> getVehicules(){
		return carRepository.findAll();
	}
	
}
