package fr.eventmanager.dao;

import fr.eventmanager.exception.MailNotFoundException;
import fr.eventmanager.exception.WrongPasswordException;
import fr.eventmanager.model.Event;
import fr.eventmanager.model.User;

import java.util.Set;

/**
 * Created by guillaume-chs on 21/10/16.
 */
public interface UserDAO extends DAO<Integer, User> {
    
    Set<Event> getUserEvents(User user);

    Set<Event> getUserEvents(Integer id);

    User findByCredentials(String email) throws MailNotFoundException;

    User findByCredentials(String email, String password) throws MailNotFoundException, WrongPasswordException;
}
