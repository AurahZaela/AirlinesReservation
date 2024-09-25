package com.synergisticit.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.User;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "user.username.value", "must have username");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "user.password.value", "must have password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roles", "user.roles.value", "must have one or more roles");
    }

}
