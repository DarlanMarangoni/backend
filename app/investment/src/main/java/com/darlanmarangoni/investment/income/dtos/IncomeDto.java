package com.darlanmarangoni.investment.income.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record IncomeDto(
        String name,
        String type,
        String description,
        BigDecimal value,
        LocalDate date,
        UUID userId
) {
}
