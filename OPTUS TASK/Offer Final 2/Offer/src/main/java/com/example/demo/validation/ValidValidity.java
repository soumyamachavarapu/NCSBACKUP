package com.example.demo.validation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidValidityValidator.class)
public @interface ValidValidity {

	String message() default "Invalid validity.Validity should in multiples of a week";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
