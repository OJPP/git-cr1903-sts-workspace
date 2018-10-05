package com.apress.prospring3.ch15.service;

import java.util.List;

import com.apress.prospring3.ch15.domain.Car;

public interface CarService {

	public List<Car> findAll();

	public Car save(Car car);

	public void updateCarAgeJob();

}
