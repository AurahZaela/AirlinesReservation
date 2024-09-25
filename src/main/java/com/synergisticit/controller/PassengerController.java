package com.synergisticit.controller;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.IdentificationType;
import com.synergisticit.domain.Passenger;
import com.synergisticit.service.PassengerService;
import com.synergisticit.validation.PassengerValidator;
import com.synergisticit.domain.Gender;

import jakarta.validation.Valid;

@Controller
public class PassengerController {
    @Autowired PassengerService passengerService;
    @Autowired PassengerValidator passengerValidator;
    
    @RequestMapping("passengerForm")
    public String passengerForm(Passenger passenger, Model model) {
        modelData(model);
        return "passengerForm";
    }
    
    @RequestMapping("savePassenger")
    public String savePassenger(@Valid @ModelAttribute Passenger passenger, BindingResult br, Model model) {  
        passengerValidator.validate(passenger, br);
        
        if (!br.hasErrors()) {
            passengerService.save(passenger);

            return "redirect:passengerForm";  
        }

        modelData(model);
        return "passengerForm";  
    }
    
    @RequestMapping("updatePassenger")
    public String updatePassenger(Passenger passenger, Model model) { 
        Passenger retrievedPassenger = passengerService.getById(passenger.getPassengerId());
        model.addAttribute("retrievedPassenger", retrievedPassenger);
        
        modelData(model);
        return "passengerForm";
    }
    
    @RequestMapping("deletePassenger")
    public String deletePassenger(Passenger passenger, Model model) {
        passengerService.deleteById(passenger.getPassengerId());
        
        modelData(model);
        return "redirect:passengerForm";
    }
    
    private void modelData(Model model) {
        model.addAttribute("passengers", passengerService.getAll());
        model.addAttribute("identificationTypes", IdentificationType.values());
        model.addAttribute("gender", Gender.values());
        
    }
}
