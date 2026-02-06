package org.mydomain.shelterspringboot.model;

public class Staff extends User {

    public Staff(Long id, String firstName, String lastName, String username, String password, String email) {
        super(id, firstName, lastName, username, password, email);
    }

    protected Staff() {
        super();
    }
}
