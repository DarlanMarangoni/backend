package com.darlanmarangoni.investment.resources;

import com.darlanmarangoni.investment.domain.Investment;
import com.darlanmarangoni.investment.dtos.InvestmentDto;
import com.darlanmarangoni.investment.repositories.InvestmentRepository;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/investments")
public class InvestmentResource {

    private final InvestmentRepository repository;

    public InvestmentResource(InvestmentRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody InvestmentDto dto) {
        Investment investment = Investment.from(dto);
        if (dto.type().equals("Acoes") || dto.type().equals("Fundo Imobiliario")) {
            Mono<Investment> investmentMono = WebClient.create(String.format("https://brapi.dev/api/quote/%s?token=wLyxbAcV9Xdsn4aCvDEp7f", dto.name()))
                    .get()
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .flatMap(js -> {
                        String unitValueStr = js.get("results").get(0).get("regularMarketPrice").asText();
                        BigDecimal unitValue = new BigDecimal(unitValueStr);
                        investment.setUnitValue(unitValue);
                        investment.setTotalValue(unitValue.multiply(new BigDecimal(dto.amount())));
                        return Mono.just(repository.save(investment));
                    });
            return ResponseEntity.ok(investmentMono);
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(repository.save(Investment.from(dto)));
    }

    @GetMapping
    public ResponseEntity<List<Investment>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable UUID id) {
        Optional<Investment> investmentOptional = repository.findById(id);
        return investmentOptional.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Investment not found"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @Valid @RequestBody InvestmentDto dto) {
        Optional<Investment> investmentOptional = repository.findById(id);
        if (investmentOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Investment not found");
        }

        Investment investment = investmentOptional.get();
        Investment updatedInvestment = Investment.from(dto);
        updatedInvestment.setId(investment.getId());
        updatedInvestment.setCreatedAt(investment.getCreatedAt());

        return ResponseEntity.ok(repository.save(updatedInvestment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        Optional<Investment> investmentOptional = repository.findById(id);
        if (investmentOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Investment not found");
        }

        repository.delete(investmentOptional.get());
        return ResponseEntity.ok("Investment deleted successfully");
    }
}
