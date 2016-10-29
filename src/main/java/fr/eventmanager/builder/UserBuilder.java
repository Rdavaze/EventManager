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
    // User parameters
    private final Set<Event> events;
    private String email;
    private String password;
    private String firstname;
    private String lastname;

    public UserBuilder() {
        this.events = new HashSet<>();
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public UserBuilder setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public UserBuilder subscribeTo(Event event) {
        this.events.add(event);
        return this;
    }

    @Override
    public User build() {
        return new User(email, password, firstname, lastname, events);
    }
}
