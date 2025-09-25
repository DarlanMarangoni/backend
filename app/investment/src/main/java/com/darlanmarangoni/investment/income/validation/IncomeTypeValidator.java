package com.darlanmarangoni.investment.income.validation;

import com.darlanmarangoni.investment.income.validation.constraints.ValidIncomeType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class IncomeTypeValidator implements ConstraintValidator<ValidIncomeType, String> {

    private static final List<String> VALID_TYPES = Arrays.asList("Renda Fixa", "Fii", "Acoes");

    @Override
    public boolean isValid(String type, ConstraintValidatorContext context) {
        if (type == null) {
            return false;
        }
        return VALID_TYPES.contains(type);
    }
}
