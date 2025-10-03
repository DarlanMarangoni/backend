package com.darlanmarangoni.income.validation.constraints;

import com.darlanmarangoni.income.validation.IncomeTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = IncomeTypeValidator.class)
@Documented
public @interface ValidIncomeType {
    String message() default "Type must be one of: Renda Fixa, Fundo Imobiliario, Acoes";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
