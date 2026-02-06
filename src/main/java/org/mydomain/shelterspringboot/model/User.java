package org.mydomain.shelterspringboot.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Staff.class, name = "staff"),
        @JsonSubTypes.Type(value = Donor.class, name = "donor"),
        @JsonSubTypes.Type(value = Adopter.class, name = "adopter"),
        @JsonSubTypes.Type(value = Admin.class, name = "admin")
})
public abstract class User {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;

    public User(Long id, String firstName, String lastName, String username, String password, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    protected User(){}

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }

    public void setId(Long id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setPassword(String password) { this.password = password; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return String.format(
                "[%s] ID: %d | Username: %s | Name: %s %s | Email: %s",
                this.getClass().getSimpleName(), id, username, firstName, lastName, email
        );
    }
}
