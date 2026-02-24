package org.mydomain.shelterspringboot.model;

import java.util.List;

public interface Donatable {
    void addDonation(Donation donation);
    List<Donation> getDonationHistory();
}
