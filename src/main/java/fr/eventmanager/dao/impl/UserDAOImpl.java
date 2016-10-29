package fr.eventmanager.dao.impl;

import fr.eventmanager.dao.UserDAO;
import fr.eventmanager.exception.MailNotFoundException;
import fr.eventmanager.exception.WrongPasswordException;
import fr.eventmanager.model.Event;
import fr.eventmanager.model.User;
import fr.eventmanager.model.User_;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;

/**
 * Created by guillaume-chs on 24/10/16.
 */
public class UserDAOImpl extends AbstractDAO<Integer, User> implements UserDAO {
    private static UserDAOImpl instance = null;

    private UserDAOImpl() {
        super();
    }

    public static UserDAOImpl getInstance() {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;

    }

    @Override
    public Set<Event> getUserEvents(User user) {
        return user.getEvents();
    }

    @Override
    public Set<Event> getUserEvents(Integer id) {
        return this.getUserEvents(findById(id));
    }

    @Override
    public User findByCredentials(String email) throws MailNotFoundException {
        final List<User> results = getEntityManagerService().performQuery(em -> {
            CriteriaBuilder cb = em.getCriteriaBuilder();

            CriteriaQuery<User> cq = cb.createQuery(getEntityClass());
            Root<User> root = cq.from(getEntityClass());

            cq.where(cb.and(cb.equal(root.get(User_.email), email)));

            TypedQuery<User> q = em.createQuery(cq);
            return q.getResultList();
        });

        if (results.isEmpty()) {
            throw new MailNotFoundException(email + " does not exist in the database");
        }
        return results.get(0);
    }

    @Override
    public User findByCredentials(String email, String password) throws MailNotFoundException, WrongPasswordException {
        final User user = this.findByCredentials(email);

        if (!user.getPassword().equals(password)) {
            throw new WrongPasswordException("Password does not match user with email " + email);
        }
        return user;
    }

    @Override
    public void updateUser(Integer id, User newUser) {

        getEntityManagerService().performQuery(em -> {

            User currentUser = em.find(getEntityClass(), id);

            em.getTransaction().begin();

            currentUser.setFirstname(newUser.getFirstname());
            currentUser.setLastname(newUser.getLastname());
            currentUser.setEmail(newUser.getEmail());
            currentUser.setPassword(newUser.getPassword());

            em.getTransaction().commit();

            return null;
        });
    }

    @Override
    public boolean emailExists(String email) {
        try {
            this.findByCredentials(email);
            return true;
        } catch (MailNotFoundException e) {
            return false;
        }
    }

    @Override
    public boolean passwordExists(String password) {
        final List<User> results = getEntityManagerService().performQuery(em -> {
            CriteriaBuilder cb = em.getCriteriaBuilder();

            CriteriaQuery<User> cq = cb.createQuery(getEntityClass());
            Root<User> root = cq.from(getEntityClass());

            cq.where(cb.equal(root.get(User_.password), password));

            TypedQuery<User> q = em.createQuery(cq);
            return q.getResultList();
        });

        return !results.isEmpty();
    }

    @Override
    public void updatePassword(String email, String password) {
        try {
            final User user = findByCredentials(email);

            getEntityManagerService().performQuery(em -> {
                User currentUser = em.find(getEntityClass(), user.getId());

                em.getTransaction().begin();
                currentUser.setPassword(password);
                em.getTransaction().commit();

                return null;
            });
        } catch (MailNotFoundException ignored) {
        }
    }
}
