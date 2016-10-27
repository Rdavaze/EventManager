package fr.eventmanager.dao;

import fr.eventmanager.model.Event;
import fr.eventmanager.model.User;

import java.util.Optional;
import java.util.Set;

/**
 * Created by guillaume-chs on 21/10/16.
 */
public interface UserDAO extends DAO<Integer, User> {
    String QUERY_USERS = "SELECT e FROM %s e";

    default String getQueryUsers(String className) {
        return String.format(QUERY_USERS, className);
    }

    Set<Event> getUserEvents(User user);

    Set<Event> getUserEvents(Integer id);

    Optional<User> findByCredentials(String email, String password);
}
