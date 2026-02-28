package org.mydomain.shelterspringboot.repository;

import org.mydomain.shelterspringboot.model.Adopter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdopterRepository extends JpaRepository<Adopter, Long> {
}