package org.mydomain.shelterspringboot.service;

import jakarta.transaction.Transactional;
import org.mydomain.shelterspringboot.model.Donatable;
import org.mydomain.shelterspringboot.model.Donation;
import org.mydomain.shelterspringboot.model.User;
import org.mydomain.shelterspringboot.repository.DonationRepository;
import org.mydomain.shelterspringboot.repository.FinancialTransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DonationService {
    private final DonationRepository donationRepository;
    private final FinancialTransactionRepository transactionRepository;

    public DonationService(DonationRepository donationRepository, FinancialTransactionRepository transactionRepository) {
        this.donationRepository = donationRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public void registerDonation(User user, double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Donation amount must be greater than 0.");
        if (!(user instanceof Donatable)) throw new IllegalArgumentException("This user type cannot make donations.");
        donationRepository.save(new Donation(amount, LocalDate.now(), user));
    }

    public List<Donation> getDonationsByUser(User user) {
        return donationRepository.findByUser(user);
    }

    public List<Donation> getDonationHistoryByUser(User user) {
        return donationRepository.findByUserOrderByDateDesc(user);
    }

    public List<Donation> getLargeDonations(double minAmount) {
        return donationRepository.findByAmountGreaterThanEqual(minAmount);
    }

    public boolean hasUserDonated(User user) {
        return donationRepository.existsByUser(user);
    }

    public double calculateTotalDonatedBy(User user) {
        Double total = transactionRepository.sumDonationsByUserId(user.getId());
        return total != null ? total : 0.0;
    }
}