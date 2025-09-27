package com.darlanmarangoni.income.resources;

import com.darlanmarangoni.income.domain.Income;
import com.darlanmarangoni.income.dtos.IncomeDto;
import com.darlanmarangoni.income.repositories.IncomeRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/income")
public class IncomeResource {

    private final IncomeRepository repository;

    public IncomeResource(IncomeRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody IncomeDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(repository.save(Income.from(dto)));
    }

    @GetMapping
    public ResponseEntity<List<Income>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable UUID id) {
        Optional<Income> incomeOptional = repository.findById(id);
        return incomeOptional.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Income not found"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @Valid @RequestBody IncomeDto dto) {
        Optional<Income> incomeOptional = repository.findById(id);
        if (incomeOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Income not found");
        }

        Income income = incomeOptional.get();
        Income updatedIncome = Income.from(dto);
        updatedIncome.setId(income.getId());
        updatedIncome.setCreatedAt(income.getCreatedAt());

        return ResponseEntity.ok(repository.save(updatedIncome));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        Optional<Income> incomeOptional = repository.findById(id);
        if (incomeOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Income not found");
        }

        repository.delete(incomeOptional.get());
        return ResponseEntity.ok("Income deleted successfully");
    }
}
