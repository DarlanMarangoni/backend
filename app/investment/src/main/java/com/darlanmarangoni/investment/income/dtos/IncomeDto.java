package com.darlanmarangoni.investment.income.dtos;

import com.darlanmarangoni.investment.income.validation.constraints.ValidIncomeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record IncomeDto(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Type is required")
        @ValidIncomeType
        String type,

        String description,

        @NotNull(message = "Value is required")
        @Positive(message = "Value must be positive")
        BigDecimal value,

        @NotNull(message = "Date is required")
        LocalDate date,

        @NotNull(message = "User ID is required")
        UUID userId
) {
}