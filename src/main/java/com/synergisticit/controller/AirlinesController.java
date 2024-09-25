package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Airlines;
import com.synergisticit.service.AirlinesService;
import com.synergisticit.validation.AirlineValidator;

import jakarta.validation.Valid;

@Controller
public class AirlinesController {
	
	@Autowired
	AirlinesService airlinesService;
	
	@Autowired
	AirlineValidator airlinesValidator;
	
	 private ModelAndView getModelAndView(){ 
	        ModelAndView modelAndView = new ModelAndView("airlinesForm");
	        return modelAndView;
	    }
	 
	 @RequestMapping("airlinesForm")
	 public ModelAndView airlinesForm(Airlines airline) {
		 ModelAndView mav = getModelAndView();
		 List<Airlines> airlines = airlinesService.getAll();
		 
		 mav.addObject("airlines", airlines);
		 mav.addObject("airline", airline);
		 
		 return mav;
	 }
	 
	 @RequestMapping("saveAirline")
	 public ModelAndView saveAirline(@Valid @ModelAttribute Airlines airline, BindingResult br) {
		 airlinesValidator.validate(airline, br);
		 //ModelAndView mav = getModelAndView();
		 if(!br.hasErrors()) {
			 airlinesService.save(airline);
			 return new ModelAndView("redirect:/airlinesForm");
		 }
		 return new ModelAndView("redirect:/airlinesForm");
	 }
	 
	 @RequestMapping("updateAirline")
	 public ModelAndView udpateAirline(Airlines airline) {
		 ModelAndView mav = getModelAndView();
		 Airlines airToUpdate = airlinesService.getById(airline.getAirlinesId());
		 List<Airlines> airlines = airlinesService.getAll();
		 
		 mav.addObject("airlines", airlines);
		 mav.addObject("airline", airToUpdate);
		 return mav;
	 }
	 
	 @RequestMapping("deleteAirline")
	 public ModelAndView deleteAirline(Airlines airline) {
		 airlinesService.deleteById(airline.getAirlinesId());
		 return new ModelAndView("redirect:/airlinesForm");
	 }

}
