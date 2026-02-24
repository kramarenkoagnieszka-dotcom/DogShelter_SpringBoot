package org.mydomain.shelterspringboot.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Admin")
public class Admin extends User {

    public Admin(String firstName, String lastName, String username, String password, String email) {
        super(firstName, lastName, username, password, email);
    }

    protected Admin() {
        super();
    }
}
