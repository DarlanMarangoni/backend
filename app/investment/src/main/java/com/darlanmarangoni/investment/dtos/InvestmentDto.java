package com.darlanmarangoni.investment.dtos;

import com.darlanmarangoni.investment.validation.constraints.ValidInvestmentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record InvestmentDto(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Type is required")
        @ValidInvestmentType
        String type,

        String description,

        @NotNull(message = "Unit value is required")
        @Positive(message = "Unit value must be positive")
        BigDecimal unitValue,

        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be positive")
        int amount,

        @NotNull(message = "Date is required")
        LocalDate date,

        @NotNull(message = "User ID is required")
        UUID userId
) {
}
