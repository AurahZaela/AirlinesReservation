package com.synergisticit.controller.userFunctionality;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.Passenger;
import com.synergisticit.domain.Reservation;
import com.synergisticit.service.PassengerService;
import com.synergisticit.service.ReservationService;

@Controller
public class MyReservationsController {
    
    @Autowired PassengerService passengerService;
    @Autowired ReservationService reservationService;
    
    @RequestMapping("myReservations")
    public String myReservations(Passenger passenger, Model model) {
        modelData(model);
        return "myReservations";
    }
    
    @RequestMapping("passengersReservations")
    public String passengersReservations(Passenger passenger, Model model) {  // passenger object with selected passengerId only is created when submitting the form from jsp
        
        long passengerId = passenger.getPassengerId();
        Passenger selectedPassenger = passengerService.getById(passengerId);
        model.addAttribute("selectedPassenger", selectedPassenger);
        
        // Have a method to get a list of reservations with given passengerId
        List<Reservation> reservationsOfPassenger = reservationService.findByPassengerId(passengerId);
        model.addAttribute("reservationsOfPassenger", reservationsOfPassenger);
        
        modelData(model);
        return "myReservations";
    }
    
    private void modelData(Model model) {
        model.addAttribute("passengers", passengerService.getAll());
        model.addAttribute("reservations", reservationService.findAll());
    }
    
}
