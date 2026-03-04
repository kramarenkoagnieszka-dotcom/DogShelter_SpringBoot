package org.mydomain.shelterspringboot.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("adopter")
public class Adopter extends User implements Donatable {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private AdopterProfile profile;

    public Adopter(String firstName, String lastName, String username, String password, String email) {
        super(firstName, lastName, username, password, email);
    }

    public Adopter(String firstName, String lastName, String username, String password, String email, AdopterProfile profile) {
        super(firstName, lastName, username, password, email);
        this.profile = profile;
    }

    protected Adopter() {
        super();
    }

    public AdopterProfile getProfile() {
        return profile;
    }

    public void setProfile(AdopterProfile profile) {
        this.profile = profile;
    }
}