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

import com.synergisticit.domain.Passenger;
import com.synergisticit.service.PassengerService;
import com.synergisticit.validation.PassengerValidator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("r3")
public class PassengerRestController {
	@Autowired
	PassengerService passengerService;
	
	@Autowired
	PassengerValidator passengerValidator; 
	
	@PostMapping("passenger/save")
	public ResponseEntity<?> saveAirline(@Valid @RequestBody Passenger passenger, BindingResult br){
		passengerValidator.validate(passenger, br);
		Long passengerId = passenger.getPassengerId();
		
		if(passengerService.existsById(passengerId)) {
			return new ResponseEntity<String>("Passenger already exists with id = " + passengerId, HttpStatus.FOUND);
		}
		
		else if(br.hasFieldErrors()) {
			String errorMessage = "Invalid input for following properties:\n";
            for (FieldError f : br.getFieldErrors()) {
                errorMessage += f.getField() + ": " + f.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<String>(errorMessage, HttpStatus.OK);
		}
		
		else
			passengerService.save(passenger);
			return new ResponseEntity<Passenger>(passenger,HttpStatus.CREATED);
		
	}
	
	@PutMapping("passenger/update")
	public ResponseEntity<?> updateAirline(@Valid @RequestBody Passenger passenger, BindingResult br){
		passengerValidator.validate(passenger, br);
		
		Long passengerId = passenger.getPassengerId();
		if(!passengerService.existsById(passengerId)) {
			return new ResponseEntity<String>("Passenger does not exist with id= " + passengerId, HttpStatus.NOT_FOUND);
		}
		
		else if(br.hasFieldErrors()) {
			String errorMessage = "Invalid input for following properties:\n";
            for (FieldError f : br.getFieldErrors()) {
                errorMessage += f.getField() + ": " + f.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<String>(errorMessage, HttpStatus.OK);
		}
		
		else 
			passengerService.save(passenger);
			return new ResponseEntity<Passenger>(passenger,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("passenger/getAll")
	public ResponseEntity<List<Passenger>> getAllAirlines(){
		List<Passenger> airlines = passengerService.getAll();
		return new ResponseEntity<List<Passenger>>(airlines, HttpStatus.FOUND);
	}
	
	@DeleteMapping("passenger/delete")
    public ResponseEntity<String> deleteFlightById(@RequestParam Long passengerId) {
        if (passengerService.existsById(passengerId)) {
        	passengerService.deleteById(passengerId);
            return new ResponseEntity<String>("Passenger deleted with id=" + passengerId, HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<String>("Passenger does not exist with id=" + passengerId, HttpStatus.NOT_FOUND);
        }
    }

}
