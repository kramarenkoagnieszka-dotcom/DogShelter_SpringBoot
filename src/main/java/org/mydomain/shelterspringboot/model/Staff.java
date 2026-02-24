package org.mydomain.shelterspringboot.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("staff")
public class Staff extends User {

    public Staff(String firstName, String lastName, String username, String password, String email) {
        super(firstName, lastName, username, password, email);
    }

    protected Staff() {
        super();
    }
}
