package com.darlanmarangoni.investment.validation;

import com.darlanmarangoni.investment.validation.constraints.ValidInvestmentType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class InvestmentTypeValidator implements ConstraintValidator<ValidInvestmentType, String> {

    private static final List<String> VALID_TYPES = Arrays.asList("Acoes", "FIIs", "Tesouro Direto", "CDB", "LCI", "LCA");

    @Override
    public boolean isValid(String type, ConstraintValidatorContext context) {
        if (type == null) {
            return false;
        }
        return VALID_TYPES.contains(type);
    }
}
