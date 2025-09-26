package com.darlanmarangoni.investment.income.domain;

import com.darlanmarangoni.investment.income.dtos.IncomeDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "description")
    private String description;
    @Column(name = "income_value")
    private BigDecimal value;
    @Column(name = "income_date")
    private LocalDate date;
    @Column(name = "user_id")
    private UUID userId;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public static Income from(IncomeDto dto) {
        var target = new Income();
        BeanUtils.copyProperties(dto, target);
        return target;
    }

    /**
     * Updates this income with data from the provided DTO
     * @param dto Data Transfer Object with updated values
     * @return this income with updated values
     */
    public Income update(IncomeDto dto) {
        this.name = dto.name();
        this.type = dto.type();
        this.description = dto.description();
        this.value = dto.value();
        this.date = dto.date();
        this.userId = dto.userId();
        return this;
    }
}
