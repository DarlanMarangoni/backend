package com.darlanmarangoni.investment.income.resources;

import com.darlanmarangoni.investment.income.domain.Income;
import com.darlanmarangoni.investment.income.dtos.IncomeDto;
import com.darlanmarangoni.investment.income.repositories.IncomeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IncomeResource {

    private final IncomeRepository repository;

    public IncomeResource(IncomeRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/income")
    public ResponseEntity<Object> create(@RequestBody IncomeDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(repository.save(Income.from(dto)));
    }
}
