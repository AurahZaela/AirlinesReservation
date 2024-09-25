package com.synergisticit.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Airlines;
import com.synergisticit.domain.Flight;
import com.synergisticit.service.AirlinesService;
import com.synergisticit.service.FlightService;
import com.synergisticit.validation.FlightValidator;

import jakarta.validation.Valid;

@Controller
public class FlightController {
	@Autowired
	FlightService flightService;
	
	@Autowired
	FlightValidator flightValidator;
	
	@Autowired
	AirlinesService airlinesService;
	
	private ModelAndView getModelAndView(){ 
        ModelAndView modelAndView = new ModelAndView("flightForm");
        return modelAndView;
    }
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping("flightForm")
	public ModelAndView flightForm(Flight flight) {
		ModelAndView mav = getModelAndView();
		List<Flight> flights = flightService.getAll();
		List<Airlines> airlines = airlinesService.getAll();
		
		mav.addObject("flight", flight);
		mav.addObject("flights", flights);
		mav.addObject("airlinesList", airlines);
	
		return mav;
	}
	
	
	@RequestMapping("saveFlight")
	public ModelAndView saveFlight(@Valid @ModelAttribute Flight flight, BindingResult br) {
		flightValidator.validate(flight, br);
		if(!br.hasErrors()) {
			System.out.println("Successfully Saved " + flight);
			flightService.save(flight);
			return new ModelAndView("redirect:/flightForm");
		}
		
		System.out.println("Problem Saving " + flight);
		return new ModelAndView("redirect:/flightForm");

	}
	
	@RequestMapping("updateFlight")
	public ModelAndView updateFlight(Flight flight) {
		ModelAndView mav = getModelAndView();
		Flight flightToUpdate = flightService.getById(flight.getFlightId());
		List<Flight> flights = flightService.getAll();
		
		mav.addObject("flight", flightToUpdate);
		mav.addObject("flights", flights);
		
		return mav;
	}
	
	@RequestMapping("deleteFlight")
	public ModelAndView deleteFlight(Flight flight) {
		flightService.deleteById(flight.getFlightId());
		return new ModelAndView("redirect:/flightForm");
	}
}
