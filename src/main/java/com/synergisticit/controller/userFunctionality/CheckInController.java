package com.synergisticit.controller.userFunctionality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.synergisticit.domain.Reservation;
import com.synergisticit.service.ReservationService;
import com.synergisticit.validation.ReservationValidator;

@Controller
public class CheckInController {
    
    @Autowired ReservationService reservationService;
    @Autowired ReservationValidator reservationValidator;
        
    @RequestMapping("checkIn")
    public String checkIn(@RequestParam("ticketNumber") long ticketNumber, Model model) {
        
        boolean checkInSuccess = false;
        
        Reservation reservation = reservationService.getById(ticketNumber);
        reservation.setCheckedIn(true);
        
        Errors br = new BeanPropertyBindingResult(reservation, "reservation");
        reservationValidator.validate(reservation, br);
        
        if (!br.hasErrors()) {
            reservationService.save(reservation);
            checkInSuccess = true;
            model.addAttribute("rsv", reservation);
        }
        else {
            String errorMessage = "Invalid input for following properties:\n";
            for (FieldError f : br.getFieldErrors()) {
                errorMessage += f.getField() + ": " + f.getDefaultMessage() + "\n";
            }
            System.out.println(errorMessage);
        }
        
        model.addAttribute("checkInSuccess", checkInSuccess);
        
        return "checkIn";
    }
    
}
