package fr.eventmanager.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * User entity to store user's details + credentials.
 */
@Entity
@Table(name = User.tableName)
public class User implements Serializable {
    static final String tableName = "User";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 25)
    private String password;

    @Column(name = "firstname", nullable = false, length = 100)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 100)
    private String lastname;

    @Column(name = "company", length = 100)
    private String company;

    @ManyToMany(mappedBy = "attendees")
    private Set<Event> events;

    public User() {
    }

    public User(String email, String password, String firstname, String lastname, String company, Set<Event> events) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.events = events;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFullname() {
        return firstname + " " + lastname;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    @Override
    public int hashCode() {
        return (id != null) ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;

        if (o instanceof User) {
            final User other = (User) o;
            return this.id.equals(other.id) || this.email.equals(other.email);
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("%s %s (%s) : %s", firstname, lastname.toUpperCase(), company, email);
    }
}
