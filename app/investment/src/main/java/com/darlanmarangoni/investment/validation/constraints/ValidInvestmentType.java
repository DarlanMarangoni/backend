package com.darlanmarangoni.investment.validation.constraints;

import com.darlanmarangoni.investment.validation.InvestmentTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = InvestmentTypeValidator.class)
@Documented
public @interface ValidInvestmentType {
    String message() default "Type must be one of: Acoes, FIIs, Tesouro Direto, CDB, LCI, LCA";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
