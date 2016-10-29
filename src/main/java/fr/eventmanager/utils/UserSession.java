package fr.eventmanager.utils;

import fr.eventmanager.model.User;

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Generic interface to keep user session's parameters as constants.
 */
public interface UserSession {
    String USER_ATTR_NAME = "user";
    String LOGGED_ATTR_NAME = "logged";

    Optional<User> getSessionUser(HttpSession session);

    void setSessionUser(HttpSession session, User user);

    Boolean isSessionLogged(HttpSession session);

    void setSessionLogged(HttpSession session, Boolean logged);
}
