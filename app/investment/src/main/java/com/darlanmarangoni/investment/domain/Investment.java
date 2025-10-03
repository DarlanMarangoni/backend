package com.darlanmarangoni.investment.domain;

import com.darlanmarangoni.investment.dtos.InvestmentDto;
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
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "description")
    private String description;
    @Column(name = "unit_value")
    private BigDecimal unitValue;
    @Column(name = "total_value")
    private BigDecimal totalValue;
    @Column(name = "amount")
    private int amount;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "user_id")
    private UUID userId;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public static Investment from(InvestmentDto dto) {
        var target = new Investment();
        BeanUtils.copyProperties(dto, target);
        return target;
    }
}
