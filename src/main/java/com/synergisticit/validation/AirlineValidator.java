package com.synergisticit.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Airlines;

@Component
public class AirlineValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		 return Airlines.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "airlinesName", "airlines.airlinesName.value", "must have airlines name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "airlinesCode", "airlines.airlinesCode.value", "must have airlines code");
	}

}
