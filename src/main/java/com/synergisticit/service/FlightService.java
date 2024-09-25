package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Flight;

public interface FlightService {
	Flight save(Flight flight);
    Flight getById(Long id);
    List<Flight> getAll();
    void deleteById(Long id);
    boolean existById(Long id);
    
    
    List<String> getAllArrCity();
    List<String> getAllDepCity();
    List<Flight> getAllByArrAndDepCity(String arrCity, String depCity);
}
