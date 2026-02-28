package org.mydomain.shelterspringboot.repository;

import org.mydomain.shelterspringboot.model.FinancialTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FinancialTransactionRepository extends JpaRepository<FinancialTransaction, Long> {

    List<FinancialTransaction> findByDate(LocalDate date);
    List<FinancialTransaction> findByDateBetween(LocalDate start, LocalDate end);
    List<FinancialTransaction> findByAmountGreaterThanEqual(double amount);
    List<FinancialTransaction> findTop10ByOrderByDateDesc();
}
