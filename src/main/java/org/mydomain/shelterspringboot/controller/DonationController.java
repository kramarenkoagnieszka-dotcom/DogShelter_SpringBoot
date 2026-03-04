package org.mydomain.shelterspringboot.controller;

import org.mydomain.shelterspringboot.model.Donation;
import org.mydomain.shelterspringboot.model.User;
import org.mydomain.shelterspringboot.service.DonationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donations")
public class DonationController {

    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerDonation(@RequestBody User user, @RequestParam double amount) {
        donationService.registerDonation(user, amount);
    }

    @GetMapping("/user")
    public List<Donation> getDonationHistory(@RequestBody User user) {
        return donationService.getDonationHistoryByUser(user);
    }

    @GetMapping("/large")
    public List<Donation> getLargeDonations(@RequestParam double minAmount) {
        return donationService.getLargeDonations(minAmount);
    }

    @GetMapping("/total")
    public double getTotalDonated(@RequestBody User user) {
        return donationService.calculateTotalDonatedBy(user);
    }
}