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
    private String prenom;
    private String nom;
    private Date birthdate;

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

    public UserBuilder setPrenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public UserBuilder setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public UserBuilder setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public UserBuilder subscribeTo(Event event) {
        this.events.add(event);
        return this;
    }

    @Override
    public User build() {
        return new User(email, password, prenom, nom, birthdate, events);
    }
}
