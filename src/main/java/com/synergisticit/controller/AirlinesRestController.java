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

import com.synergisticit.domain.Airlines;
import com.synergisticit.service.AirlinesService;
import com.synergisticit.validation.AirlineValidator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("r1")
public class AirlinesRestController {
	
	@Autowired
	AirlinesService airlineService;
	
	@Autowired
	AirlineValidator airlineValidator; 
	
	@PostMapping("airline/save")
	public ResponseEntity<?> saveAirline(@Valid @RequestBody Airlines airline, BindingResult br){
		airlineValidator.validate(airline, br);
		Long airlineId = airline.getAirlinesId();
		
		if(airlineService.existById(airlineId)) {
			return new ResponseEntity<String>("Airline already exists with id = " + airlineId, HttpStatus.FOUND);
		}
		
		else if(br.hasFieldErrors()) {
			String errorMessage = "Invalid input for following properties:\n";
            for (FieldError f : br.getFieldErrors()) {
                errorMessage += f.getField() + ": " + f.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<String>(errorMessage, HttpStatus.OK);
		}
		
		else
			airlineService.save(airline);
			return new ResponseEntity<Airlines>(airline,HttpStatus.CREATED);
		
	}
	
	@PutMapping("airline/update")
	public ResponseEntity<?> updateAirline(@Valid @RequestBody Airlines airline, BindingResult br){
		airlineValidator.validate(airline, br);
		
		Long airlineId = airline.getAirlinesId();
		if(!airlineService.existById(airlineId)) {
			return new ResponseEntity<String>("Airline does not exist with id= " + airlineId, HttpStatus.NOT_FOUND);
		}
		
		else if(br.hasFieldErrors()) {
			String errorMessage = "Invalid input for following properties:\n";
            for (FieldError f : br.getFieldErrors()) {
                errorMessage += f.getField() + ": " + f.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<String>(errorMessage, HttpStatus.OK);
		}
		
		else 
			airlineService.save(airline);
			return new ResponseEntity<Airlines>(airline,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("airline/getAll")
	public ResponseEntity<List<Airlines>> getAllAirlines(){
		List<Airlines> airlines = airlineService.getAll();
		return new ResponseEntity<List<Airlines>>(airlines, HttpStatus.FOUND);
	}
	
	@DeleteMapping("airline/delete")
    public ResponseEntity<String> deleteFlightById(@RequestParam Long airlineId) {
        if (airlineService.existById(airlineId)) {
        	airlineService.deleteById(airlineId);
            return new ResponseEntity<String>("Airline deleted with id=" + airlineId, HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<String>("Airline does not exist with id=" + airlineId, HttpStatus.NOT_FOUND);
        }
    }

}
