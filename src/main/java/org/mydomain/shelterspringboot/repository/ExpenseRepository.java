package org.mydomain.shelterspringboot.repository;

import org.mydomain.shelterspringboot.model.Dog;
import org.mydomain.shelterspringboot.model.Expense;
import org.mydomain.shelterspringboot.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByDog(Dog dog);
    List<Expense> findByStaff(Staff staff);
    List<Expense> findByDescriptionContainingIgnoreCase(String keyword);

}
