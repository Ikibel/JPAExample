package com.example.RentCarTry;

import java.util.ArrayList;
import java.util.List;



import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

@RestController
public class CarRentalService {
	
	private List<Car> cars = new ArrayList<Car>();
	
	public CarRentalService() {
		cars.add(new Car("11AA22", "Ferrari", 1000, 5));
		cars.add(new Car("33BB44", "Porshe", 2222, 2));
		cars.add(new Car("55CC66", "Audi", 3500, 5));
	}
	
	@Bean
	public CommandLineRunner demo(CarRepository repository) {
		return (args) -> {
			
			Car car1 = new Car("11AA22", "Ferrari", 1000, 5);
			Car car2 = new Car("33BB44", "Porshe", 2222, 2);
			Car car3 = new Car("55CC66", "Audi", 3500, 5) ;
			
			repository.save(car1);
			repository.save(car2);
			repository.save(car3);
		};
	
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/cars", method=RequestMethod.GET) 
	@ResponseStatus(HttpStatus.OK) 
	public List<Car> getListOfCars(){
		return cars;
	}
	
	
	@RequestMapping(value = "/cars", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void addCar(@RequestBody Car car) throws Exception{
		System.out.println(car);
		cars.add(car);
	}
	
	


	
	
	@RequestMapping(value = "/cars/{plateNumber}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Car aCar(@PathVariable("plateNumber") String plateNumber) throws Exception{
		System.out.println(plateNumber);
		for(Car Car : cars) {
			if(Car.getPlateNumber().contentEquals(plateNumber)) {
				System.out.println(Car);
				return Car;

			}
		}
		return null;
	}
	
	
	
	
	@RequestMapping(value = "/cars/{plateNumber}", method = RequestMethod.PUT)
	//@ResponseStatus(HttpStatus.OK)
	public void rentAndGetBack(@PathVariable("plateNumber") String plateNumber,
	@RequestParam(value="rent", required = true)boolean rent,
	@RequestBody(required = false) Dates dates){
		
		for(Car car : cars) {
			if(car.getPlateNumber().equals(plateNumber)) {
				if (rent == true) {
						System.out.println();
						car.setRent(true);
						car.setDateDebut(dates.getBegin());
						car.setDateFin(dates.getEnd());
					}
				else if (rent == false) {
					car.setRent(false);
					car.setDateDebut(null);
					car.setDateFin(null);
					
				}
			}
		}
	}
}
	
