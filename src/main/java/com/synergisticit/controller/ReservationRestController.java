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

import com.synergisticit.domain.Reservation;
import com.synergisticit.service.ReservationService;
import com.synergisticit.validation.ReservationValidator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("r4")
public class ReservationRestController {
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	ReservationValidator reservationValidator; 
	
	@PostMapping("reservation/save")
	public ResponseEntity<?> saveFlight(@Valid @RequestBody Reservation reservation, BindingResult br){
		reservationValidator.validate(reservation, br);
		Long ticketNumber = reservation.getTicketNumber();
		
		if(reservationService.existsById(ticketNumber)) {
			return new ResponseEntity<String>("Reservation already exists with id = " + ticketNumber, HttpStatus.FOUND);
		}
		
		else if(br.hasFieldErrors()) {
			String errorMessage = "Invalid input for following properties:\n";
            for (FieldError f : br.getFieldErrors()) {
                errorMessage += f.getField() + ": " + f.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<String>(errorMessage, HttpStatus.OK);
		}
		
		else
			reservationService.save(reservation);
			return new ResponseEntity<Reservation>(reservation,HttpStatus.CREATED);
		
	}
	
	@PutMapping("reservation/update")
	public ResponseEntity<?> updateFlight(@Valid @RequestBody Reservation reservation, BindingResult br){
		reservationValidator.validate(reservation, br);
		
		Long ticketNumber = reservation.getTicketNumber();
		if(!reservationService.existsById(ticketNumber)) {
			return new ResponseEntity<String>("Reservation does not exist with id= " + ticketNumber, HttpStatus.NOT_FOUND);
		}
		
		else if(br.hasFieldErrors()) {
			String errorMessage = "Invalid input for following properties:\n";
            for (FieldError f : br.getFieldErrors()) {
                errorMessage += f.getField() + ": " + f.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<String>(errorMessage, HttpStatus.OK);
		}
		
		else 
			reservationService.save(reservation);
			return new ResponseEntity<Reservation>(reservation,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("reservation/getAll")
	public ResponseEntity<List<Reservation>> getAllFlight(){
		List<Reservation> reservations = reservationService.findAll();
		return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.FOUND);
	}
	
	@DeleteMapping("reservation/delete")
    public ResponseEntity<String> deleteFlightById(@RequestParam Long ticketNumber) {
        if (reservationService.existsById(ticketNumber)) {
        	reservationService.deleteById(ticketNumber);
            return new ResponseEntity<String>("Reservation deleted with id=" + ticketNumber, HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<String>("Reservation does not exist with id=" + ticketNumber, HttpStatus.NOT_FOUND);
        }
    }

}
