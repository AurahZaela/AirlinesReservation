package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Passenger;
import com.synergisticit.repository.PassengerRepository;

@Service
public class PassengerServiceImpl implements PassengerService {

	@Autowired
	PassengerRepository passengerRepository;
	
	@Override
	public Passenger save(Passenger passenger) {
		return passengerRepository.save(passenger);
	}

	@Override
	public Passenger getById(Long Id) {
		Optional<Passenger> optPass = passengerRepository.findById(Id);
		
		if(optPass.isPresent()) {
			return optPass.get();
		}
		else
			return null;
	}

	@Override
	public List<Passenger> getAll() {
		// TODO Auto-generated method stub
		return passengerRepository.findAll();
	}

	@Override
	public void deleteById(Long Id) {
		passengerRepository.deleteById(Id);

	}

	@Override
	public boolean existsById(Long Id) {
		return passengerRepository.existsById(Id);
	}

	@Override
	public Long getNextPassId() {
		return passengerRepository.getNextSeriesId();
	}

}
