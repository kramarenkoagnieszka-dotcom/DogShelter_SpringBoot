package org.mydomain.shelterspringboot.repository;

import org.mydomain.shelterspringboot.model.FinancialTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FinancialTransactionRepository extends JpaRepository<FinancialTransaction, Long> {
    @Query("SELECT SUM(t.amount) FROM FinancialTransaction t WHERE TYPE(t) = Donation")
    Double sumAllDonations();

    @Query("SELECT SUM(t.amount) FROM FinancialTransaction t WHERE TYPE(t) = Expense")
    Double sumAllExpenses();

    @Query("SELECT SUM(d.amount) FROM Donation d WHERE d.user.id = :userId")
    Double sumDonationsByUserId(Long userId);
    List<FinancialTransaction> findByDate(LocalDate date);
    List<FinancialTransaction> findByDateBetween(LocalDate start, LocalDate end);
    List<FinancialTransaction> findByAmountGreaterThanEqual(double amount);
    List<FinancialTransaction> findTop10ByOrderByDateDesc();
}
