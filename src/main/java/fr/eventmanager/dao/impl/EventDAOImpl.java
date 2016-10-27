package fr.eventmanager.dao.impl;

import fr.eventmanager.dao.EventDAO;
import fr.eventmanager.model.Event;
import fr.eventmanager.model.User;

import javax.persistence.Query;
import java.util.List;
import java.util.Set;

/**
 * Created by guillaume-chs on 21/10/16.
 */
public class EventDAOImpl extends AbstractDAO<Integer, Event> implements EventDAO {
    private static EventDAOImpl instance = null;

    public static EventDAOImpl getInstance() {
        if (instance == null) {
            instance = new EventDAOImpl();
        }
        return instance;

    }

    private EventDAOImpl() {
        super();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Event> findLastEvents(int nbEvents) {
        return getEntityManagerService().performQuery(em -> {
            final Query query = em.createQuery(getQueryEvents(Event.tableName));
            query.setMaxResults(nbEvents);

            return (List<Event>) query.getResultList();
        });
    }

    @Override
    public Set<User> getEventAttendees(Event event) {
        return event.getAttendees();
    }

    @Override
    public Set<User> getEventAttendees(Integer id) {
        return this.getEventAttendees(findById(id));
    }
}
