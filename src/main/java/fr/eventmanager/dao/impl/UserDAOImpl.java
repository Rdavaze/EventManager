package fr.eventmanager.dao.impl;

import fr.eventmanager.dao.UserDAO;
import fr.eventmanager.model.Event;
import fr.eventmanager.model.User;

import java.util.Set;

/**
 * Created by guillaume-chs on 24/10/16.
 */
public class UserDAOImpl extends AbstractDAO<Integer, User> implements UserDAO {
    private static UserDAOImpl instance = null;

    public static UserDAOImpl getInstance() {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;

    }

    private UserDAOImpl() {
        super();
    }

    @Override
    public Set<Event> getUserEvents(User user) {
        return user.getEvents();
    }

    @Override
    public Set<Event> getUserEvents(Integer id)  {
        return this.getUserEvents(findById(id));
    }

    private void populate() {
        getEntityManagerService().performOperation(em -> {
            em.persist(new User("john.doe@gmail.com", "password", "John", "Doe"));
            em.persist(new User("richard.roe@gmail.com", "password", "Richard", "Roe"));
        });
    }
}
