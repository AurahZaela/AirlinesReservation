package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Reservation;
import com.synergisticit.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService{
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Override
	public Reservation save(Reservation reservation) {
		return reservationRepository.save(reservation);
	}

	@Override
	public List<Reservation> findAll() {
		return reservationRepository.findAll();
	}

	@Override
	public Reservation getById(Long Id) {
		Optional<Reservation> optReservation = reservationRepository.findById(Id);
		
		if(optReservation.isPresent()) {
			return optReservation.get();
		}
		else {
			return null;
		}
	}

	@Override
	public void deleteById(Long Id) {
		reservationRepository.deleteById(Id);;
	}

	@Override
	public boolean existsById(Long Id) {
		return reservationRepository.existsById(Id);
	}

	@Override
	public List<Reservation> findByPassengerId(Long passengerId) {
		return reservationRepository.findByPassenger_passengerId(passengerId);
	}
}
