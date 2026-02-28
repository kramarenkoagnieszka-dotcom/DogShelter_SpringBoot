package org.mydomain.shelterspringboot.repository;

import org.mydomain.shelterspringboot.model.Donation;
import org.mydomain.shelterspringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {


    List<Donation> findByUser(User user);
    List<Donation> findByUserOrderByDateDesc(User user);
    List<Donation> findByAmountGreaterThanEqual(double amount);
    boolean existsByUser(User user);
}