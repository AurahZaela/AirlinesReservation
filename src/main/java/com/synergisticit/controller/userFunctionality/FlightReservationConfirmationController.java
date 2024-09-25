package com.synergisticit.controller.userFunctionality;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.synergisticit.domain.Flight;
import com.synergisticit.domain.Passenger;
import com.synergisticit.domain.Reservation;
import com.synergisticit.service.FlightService;
import com.synergisticit.service.PassengerService;
import com.synergisticit.service.ReservationService;
import com.synergisticit.validation.ReservationValidator;

@Controller
public class FlightReservationConfirmationController {
	
	@Autowired
	PassengerService passengerService;
	
	@Autowired
	FlightService flightService;
	
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	ReservationValidator reservationValidator;
	
	
	@RequestMapping("makeReservation")
	public String makeReservation(@RequestParam("passengerId") long passengerId, @RequestParam("flightId") long flightId,@RequestParam("numBags") int numBags, Model model) {
        Passenger retrievedPassenger = passengerService.getById(passengerId);
        Flight selectedFlight = flightService.getById(flightId);
        
        boolean reservationSuccess = false;  // if booking over capacity, return without reserving.
        if (selectedFlight.getBooked() >= selectedFlight.getCapacity()) {
            return "flightReservationConfirm";
        }
        
        Reservation reservation = new Reservation(retrievedPassenger, selectedFlight, false, numBags);

        
        Errors br = new BeanPropertyBindingResult(reservation, "reservation");
        reservationValidator.validate(reservation, br);
        
        if (!br.hasErrors()) {
            reservationService.save(reservation);
            selectedFlight.setBooked(selectedFlight.getBooked() + 1);
            flightService.save(selectedFlight);
            reservationSuccess = true;
            model.addAttribute("rsv", reservation);
        }
        else {
            String errorMessage = "Invalid input for following properties:\n";
            for (FieldError f : br.getFieldErrors()) {
                errorMessage += f.getField() + ": " + f.getDefaultMessage() + "\n";
            }
            System.out.println(errorMessage);
        }
        
        model.addAttribute("retrievedPassenger", retrievedPassenger);
        model.addAttribute("selectedFlight", selectedFlight);
        model.addAttribute("reservationSuccess", reservationSuccess);
        
        return "flightReservationConfirm";
    }
    
	
}
