package fr.eventmanager.model.user;

import javax.persistence.GeneratedValue;
import java.io.Serializable;

/**
 * User credentials primary key.
 */
public class UserPK implements Serializable {
    private static final long serialVersionUID = 1L;

    @GeneratedValue
    private int id;
    private String email;

    public UserPK() {
    }

    public UserPK(String email) {
        this.email = email;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return String.format("[%d]:%s", id, email).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;

        if (o instanceof UserPK) {
            final UserPK other = (UserPK) o;
            return this.id == other.id || this.email.equals(other.email);
        }
        return false;
    }
}
