package fr.eventmanager.model.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

/**
 * User's credentials.
 */
@Entity
@IdClass(UserCredentialsPK.class)
public class UserCredentials implements Serializable {
    private String email;
    private String password;


    public UserCredentials() {
        super();
    }

    @Id
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Id
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
