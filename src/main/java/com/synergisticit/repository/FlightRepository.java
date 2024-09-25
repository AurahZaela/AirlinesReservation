package com.synergisticit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.synergisticit.domain.Flight;

public interface FlightRepository extends JpaRepository<Flight,Long>{

	@Query("SELECT DISTINCT f.arrivalCity FROM Flight f")
	List<String> getAllArrCity();
	
	@Query("SELECT DISTINCT f.departureCity FROM Flight f")
	List<String> getAllDepCity();
	
	List<Flight> getByArrivalCityAndDepartureCity(String arrCity, String depCity);
}
