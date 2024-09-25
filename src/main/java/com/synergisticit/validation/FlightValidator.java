package com.synergisticit.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Flight;



@Component
public class FlightValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		 return Flight.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "flightNumber", "flight.flightNumber.value", "must have flight number");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "departureCity", "flight.departureCity.value", "must have departure city");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "arrivalCity", "flight.arrivalCity.value", "must have arrival city");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ticketPrice", "flight.ticketPrice.value", "must have ticket price");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "capacity", "flight.capacity.value", "must have capacity");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "booked", "flight.booked.value", "must have booked count");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "departureDate", "flight.departureDate.value", "must have departure date");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "departureTime", "flight.departureTime.value", "must have departure time");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "operatingAirlines", "flight.operatingAirlines.value", "must have operating airlines");
	}
}
