package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Passenger;

public interface PassengerService {
	Passenger save(Passenger passenger);
	Passenger getById(Long Id);
	List<Passenger> getAll();
	void deleteById(Long Id);
	boolean existsById(Long Id);
	
	Long getNextPassId();
}
