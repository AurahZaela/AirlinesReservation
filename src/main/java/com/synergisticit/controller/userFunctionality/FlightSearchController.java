package com.synergisticit.controller.userFunctionality;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.Flight;
import com.synergisticit.service.FlightService;

@Controller
public class FlightSearchController {

	 @Autowired
	 FlightService flightService;
	 
	 
	 @RequestMapping("flightSearch")
	 public String flightSearch(Flight flight, Model model) {
		 modelData(model);
		 return "flightSearch";
	 }
	 
	 @RequestMapping("flightSearchDetails")
	 public String flightSearchDetails(Flight flight, Model model) {
		String city1 = flight.getArrivalCity();
		String city2 = flight.getDepartureCity();
		
		
		List<Flight> listOfSearchFlights = flightService.getAllByArrAndDepCity(city1, city2);
		
		
		model.addAttribute("listOfSearchedFlights", listOfSearchFlights);
		
		
		modelData(model);
		 return "flightSearch";
	 }
	 
	 
	 private void modelData(Model model) {
		 List<String> listOfArrCity = flightService.getAllArrCity();
		 List<String> listOfDepCity = flightService.getAllDepCity();
		 
		 model.addAttribute("listOfArrCity", listOfArrCity);
		 model.addAttribute("listOfDepCity", listOfDepCity);
	 }
}
