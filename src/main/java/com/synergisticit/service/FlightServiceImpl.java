package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Flight;
import com.synergisticit.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	FlightRepository flightRepository;
	
	@Override
	public Flight save(Flight flight) {
		return flightRepository.save(flight);
	}

	@Override
	public Flight getById(Long id) {
		Optional<Flight> optFlight = flightRepository.findById(id);
		
		if(optFlight.isPresent()) {
			return optFlight.get();
		}
		else
			return null;
	}

	@Override
	public List<Flight> getAll() {
		return flightRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		flightRepository.deleteById(id);

	}

	@Override
	public boolean existById(Long id) {
		return flightRepository.existsById(id);
	}

	@Override
	public List<Flight> getAllByArrAndDepCity(String arrCity, String depCity) {
		return flightRepository.getByArrivalCityAndDepartureCity(arrCity, depCity);
	}

	@Override
	public List<String> getAllArrCity() {
//		System.out.println(flightRepository.getAllArrCity());
		return flightRepository.getAllArrCity();
	}

	@Override
	public List<String> getAllDepCity() {
		return flightRepository.getAllDepCity();
	}

}
