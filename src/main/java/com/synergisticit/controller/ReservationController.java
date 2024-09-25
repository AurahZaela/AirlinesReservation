package com.synergisticit.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.Flight;
import com.synergisticit.domain.Passenger;
import com.synergisticit.domain.Reservation;
import com.synergisticit.service.FlightService;
import com.synergisticit.service.PassengerService;
import com.synergisticit.service.ReservationService;
import com.synergisticit.validation.ReservationValidator;

import jakarta.validation.Valid;

@Controller 
public class ReservationController {
    @Autowired ReservationService reservationService;
    @Autowired PassengerService passengerService;
    @Autowired FlightService flightService;
    @Autowired ReservationValidator reservationValidator;
    

    @RequestMapping("reservationForm")
    public String reservationForm(Reservation reservation, Model model) {
        modelData(model);
        return "reservationForm";
    }
    
    @RequestMapping("saveReservation")
    public String saveReservation(@Valid @ModelAttribute Reservation reservation, BindingResult br, Model model) {  // BindingResult must come before Model, otherwise Model will send to error page before BindingResult do its job
        reservationValidator.validate(reservation, br);
        
        if (!br.hasErrors()) {
            reservationService.save(reservation);
            // do not need to call modelData(model) because it's in reservationForm method
            return "redirect:reservationForm";  // redirect to url reservationForm which calls reservationForm method
        }

        modelData(model);
        return "reservationForm";  // do not redirect, keep the info entered and show error messages
    }
    
    @RequestMapping("updateReservation")
    public String updateReservation(Reservation reservation, Model model) {  // Reservation object created with only ticketNum is passed in by request param
        Reservation retrievedReservation = reservationService.getById(reservation.getTicketNumber());
        model.addAttribute("retrievedReservation", retrievedReservation);
        
        Passenger selectedPassenger = retrievedReservation.getPassenger();
        model.addAttribute("selectedPassenger", selectedPassenger);
        
        Flight selectedFlight = retrievedReservation.getFlight();
        model.addAttribute("selectedFlight", selectedFlight);
        
        modelData(model);
        return "reservationForm";
    }
    
    @RequestMapping("deleteReservation")
    public String deleteReservation(Reservation reservation, Model model) {
        reservationService.deleteById(reservation.getTicketNumber());
        
        modelData(model);
        return "redirect:reservationForm";
    }
    
    private void modelData(Model model) {
        model.addAttribute("reservations", reservationService.findAll());
        model.addAttribute("passengers", passengerService.getAll());
        model.addAttribute("flights", flightService.getAll());
    }
}
