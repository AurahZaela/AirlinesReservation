package com.synergisticit.validation;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Passenger;

@Component
public class PassengerValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Passenger.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "passenger.firstName.value", "must have first name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "passenger.lastName.value", "must have last name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "passenger.email.value", "must have email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobileNo", "passenger.mobileNo.value", "must have phone number");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "passenger.gender.value", "must have gender");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "DOB", "passenger.DOB.value", "must have dob");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "identificationType", "passenger.identificationType.value", "must have id type");
    
        Passenger passenger = (Passenger) target;

    }

}
