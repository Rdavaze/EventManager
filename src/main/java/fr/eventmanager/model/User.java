package fr.eventmanager.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * User entity to store user's details + credentials.
 */
@Entity
@Table(name = User.tableName)
public class User implements Serializable {
    public static final String tableName = "User";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 25)
    private String password;

    @Column(name = "prenom", nullable = false, length = 100)
    private String prenom;

    @Column(name = "nom", nullable = false, length = 100)
    private String nom;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthdate")
    private Date birthdate;

    @ManyToMany(mappedBy = "attendees")
    private Set<Event> events;

    public User() {
    }

    public User(String email, String password, String prenom, String nom, Date birthdate, Set<Event> events) {
        this.email = email;
        this.password = password;
        this.prenom = prenom;
        this.nom = nom;
        this.birthdate = birthdate;
        this.events = events;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
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
        return String.format("[%d]:%s", id, email);
    }
}
