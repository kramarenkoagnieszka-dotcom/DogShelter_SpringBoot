package org.mydomain.shelterspringboot.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Entity
@DiscriminatorValue("donor")
public class Donor extends User implements Donatable {
    public Donor(String firstName, String lastName, String username, String password, String email) {
        super(firstName, lastName, username, password, email);
    }

    protected Donor() {
        super();
    }
}
