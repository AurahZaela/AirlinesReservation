package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.domain.Flight;
import com.synergisticit.service.FlightService;
import com.synergisticit.validation.FlightValidator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("r2")
public class FlightRestController {
	@Autowired
	FlightService flightService;
	
	@Autowired
	FlightValidator flightValidator; 
	
	@PostMapping("flight/save")
	public ResponseEntity<?> saveFlight(@Valid @RequestBody Flight flight, BindingResult br){
		flightValidator.validate(flight, br);
		Long flightId = flight.getFlightId();
		
		if(flightService.existById(flightId)) {
			return new ResponseEntity<String>("Flight already exists with id = " + flightId, HttpStatus.FOUND);
		}
		
		else if(br.hasFieldErrors()) {
			String errorMessage = "Invalid input for following properties:\n";
            for (FieldError f : br.getFieldErrors()) {
                errorMessage += f.getField() + ": " + f.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<String>(errorMessage, HttpStatus.OK);
		}
		
		else
			flightService.save(flight);
			return new ResponseEntity<Flight>(flight,HttpStatus.CREATED);
		
	}
	
	@PutMapping("flight/update")
	public ResponseEntity<?> updateFlight(@Valid @RequestBody Flight flight, BindingResult br){
		flightValidator.validate(flight, br);
		
		Long flightId = flight.getFlightId();
		if(!flightService.existById(flightId)) {
			return new ResponseEntity<String>("Flight does not exist with id= " + flightId, HttpStatus.NOT_FOUND);
		}
		
		else if(br.hasFieldErrors()) {
			String errorMessage = "Invalid input for following properties:\n";
            for (FieldError f : br.getFieldErrors()) {
                errorMessage += f.getField() + ": " + f.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<String>(errorMessage, HttpStatus.OK);
		}
		
		else 
			flightService.save(flight);
			return new ResponseEntity<Flight>(flight,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("flight/getAll")
	public ResponseEntity<List<Flight>> getAllFlight(){
		List<Flight> airlines = flightService.getAll();
		return new ResponseEntity<List<Flight>>(airlines, HttpStatus.FOUND);
	}
	
	@DeleteMapping("flight/delete")
    public ResponseEntity<String> deleteFlightById(@RequestParam Long flightId) {
        if (flightService.existById(flightId)) {
        	flightService.deleteById(flightId);
            return new ResponseEntity<String>("Flight deleted with id=" + flightId, HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<String>("Flight does not exist with id=" + flightId, HttpStatus.NOT_FOUND);
        }
    }

}
