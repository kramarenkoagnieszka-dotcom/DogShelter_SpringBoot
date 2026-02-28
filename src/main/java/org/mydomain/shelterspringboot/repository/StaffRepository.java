package org.mydomain.shelterspringboot.repository;

import org.mydomain.shelterspringboot.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
}