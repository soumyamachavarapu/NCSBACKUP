package com.example.demo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidValidityValidator implements ConstraintValidator<ValidValidity, Integer> {
	
	@Override
    public void initialize(ValidValidity constraintAnnotation) {
        // Initialization, if needed
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;  // Null values are not valid
        }

        // Check if the value is in multiples of a week (e.g., 28, 56)
        return value % 7 == 0;
    }

}
