package fr.eventmanager.model.user;

import java.io.Serializable;

/**
 * User credentials primary key.
 */
public class UserCredentialsPK implements Serializable {
    private static final long serialVersionUID = 1L;

    private String email;
    private String password;

    public UserCredentialsPK() {
    }

    public UserCredentialsPK(String email, String password) {
        this.email = email;
        this.password = password;
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

    @Override
    public int hashCode() {
        return String.format("%s:%s", email, password).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;

        if (o instanceof UserCredentialsPK) {
            final UserCredentialsPK other = (UserCredentialsPK) o;
            return this.email.equals(other.email) && this.password.equals(other.password);
        }
        return false;
    }
}
