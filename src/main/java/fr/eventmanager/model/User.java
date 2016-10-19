package fr.eventmanager.model;

import javax.persistence.*;
import java.util.Date;

/**
 * User entity to store user's details + credentials.
 */
@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Basic(fetch = FetchType.LAZY, optional = false)
    private String email;

    @Basic(fetch = FetchType.LAZY, optional = false)
    private String password;

    @Basic(fetch = FetchType.EAGER, optional = false)
    private String prenom;

    @Basic(fetch = FetchType.EAGER, optional = false)
    private String nom;

    @Temporal(TemporalType.DATE)
    @Basic(fetch = FetchType.LAZY, optional = true)
    private Date birthdate;

    private static final long serialVersionUID = 1L;

    public User() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;

        if (o instanceof User) {
            final User other = (User) o;
            return this.id == other.id || this.email.equals(other.email);
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("[%d]:%s", id, email);
    }
}
