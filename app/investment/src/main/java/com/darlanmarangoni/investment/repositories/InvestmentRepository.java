package com.darlanmarangoni.investment.repositories;

import com.darlanmarangoni.investment.domain.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, UUID> {
    List<Investment> findByNameContainingIgnoreCaseOrderByDateDesc(String name);
}
