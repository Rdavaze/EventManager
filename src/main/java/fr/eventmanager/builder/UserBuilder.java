package fr.eventmanager.builder;

import fr.eventmanager.model.Event;
import fr.eventmanager.model.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by guillaume-chs on 26/10/16.
 */
public class UserBuilder implements Builder<User> {
    // Non-nullable fields
    private final String email;
    private final String password;
    private final String firstname;
    private final String lastname;

    // Nullable fields
    private final Set<Event> events;
    private String company;

    public UserBuilder(String email, String password, String firstname, String lastname) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.events = new HashSet<>();
    }

    public UserBuilder setCompany(String company) {
        if (company != null) {
            this.company = company;
        }
        return this;
    }

    public UserBuilder subscribeTo(Event event) {
        if (event != null) {
            this.events.add(event);
        }
        return this;
    }

    @Override
    public User build() {
        return new User(email, password, firstname, lastname, company, events);
    }
}
