package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Reservation;

public interface ReservationService {
	Reservation save(Reservation reservation);
	List<Reservation> findAll();
	Reservation getById(Long Id);
	void deleteById(Long Id);
	boolean existsById(Long Id);
	
	List<Reservation> findByPassengerId(Long passengerId);
}
