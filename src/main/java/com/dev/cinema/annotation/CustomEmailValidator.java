package com.dev.cinema.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomEmailValidator implements ConstraintValidator<CustomEmailConstraint, String> {
    public static final String EMAIL_REGEX = "^(.+)@(.+)$";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email != null && email.matches(EMAIL_REGEX);
    }
}
