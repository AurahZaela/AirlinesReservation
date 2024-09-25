package com.synergisticit.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Reservation;

@Component
public class ReservationValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		 return Reservation.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passenger", "reservation.passenger.value", "must have passenger");
	}

}
