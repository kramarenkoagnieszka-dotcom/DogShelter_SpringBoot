package org.mydomain.shelterspringboot.repository;

import org.mydomain.shelterspringboot.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}