package fr.eventmanager.dao.impl;

import fr.eventmanager.dao.UserDAO;
import fr.eventmanager.model.Event;
import fr.eventmanager.model.User;
import fr.eventmanager.model.User_;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
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
    public Set<Event> getUserEvents(Integer id) {
        return this.getUserEvents(findById(id));
    }

    @Override
    public Optional<User> findByCredentials(String email, String password) {
        findAll().forEach(u -> System.out.println(u.getId() + " - " + u.getPrenom() + u.getNom() + " - " + u.getEmail() + ":" + u.getPassword()));

        final List<User> results = getEntityManagerService().performQuery(em -> {
            CriteriaBuilder cb = em.getCriteriaBuilder();

            CriteriaQuery<User> cq = cb.createQuery(getEntityClass());
            Root<User> root = cq.from(getEntityClass());

            cq.where(cb.and(cb.equal(root.get(User_.email), email),
                    cb.equal(root.get(User_.password), password)));

            TypedQuery<User> q = em.createQuery(cq);
            return q.getResultList();
        });

        if (!results.isEmpty()) {
            return Optional.of(results.get(0));
        } else {
            return Optional.empty();
        }
    }
}
