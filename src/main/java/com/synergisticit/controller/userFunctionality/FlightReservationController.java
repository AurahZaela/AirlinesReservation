package com.synergisticit.controller.userFunctionality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.synergisticit.domain.Flight;
import com.synergisticit.domain.Gender;
import com.synergisticit.domain.IdentificationType;
import com.synergisticit.domain.Passenger;
import com.synergisticit.service.FlightService;
import com.synergisticit.service.PassengerService;
import com.synergisticit.validation.PassengerValidator;

import jakarta.validation.Valid;

@Controller
public class FlightReservationController {
    
    @Autowired PassengerService passengerService;
    @Autowired FlightService flightService;
    @Autowired PassengerValidator passengerValidator;
    
    public long flightId;
    
    long nextPassId=1;
    
    @RequestMapping("flightReservation")
    public String flightReservation(@RequestParam("flightId") long flightId, Passenger passenger, Model model) {
        if(passengerService.getNextPassId() != null) {
        	nextPassId = passengerService.getNextPassId() + 1;
        }
    	passenger.setPassengerId(nextPassId);
    	this.flightId = flightId;  // When user clicks reserve from Flight Search page, flightId is passed
        modelData(model);
        return "flightReservation";
    }
    
    
    @RequestMapping("savePassengerForReservation")
    public String fillPassenger(@Valid @ModelAttribute Passenger passenger, BindingResult br, Model model) {  // BindingResult must come before Model
        passengerValidator.validate(passenger, br);
        
        if (!br.hasErrors()) {
            passengerService.save(passenger);
            Passenger retrievedPassenger = passengerService.getById(passenger.getPassengerId());
            model.addAttribute("retrievedPassenger", retrievedPassenger);
        }
        
        modelData(model);
        return "flightReservation";
    }
    
    @RequestMapping("updatePassengerForReservation")
    public String updatePassenger(Passenger passenger, Model model) {  // Passenger object created with only passengerId is passed in by request param
        Passenger retrievedPassenger = passengerService.getById(passenger.getPassengerId());
        model.addAttribute("retrievedPassenger", retrievedPassenger);
        
        modelData(model);
        return "flightReservation";
    }
    
    private void modelData(Model model) {
        Flight selectedFlight = flightService.getById(flightId);
        model.addAttribute("selectedFlight", selectedFlight);
        model.addAttribute("identificationTypes", IdentificationType.values());
        model.addAttribute("genders", Gender.values());
    }
}
