package fr.eventmanager.dao.impl;

import fr.eventmanager.dao.EventDAO;
import fr.eventmanager.exception.MailNotFoundException;
import fr.eventmanager.model.Event;
import fr.eventmanager.model.Event_;
import fr.eventmanager.model.User;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;

/**
 * Created by guillaume-chs on 21/10/16.
 */
public class EventDAOImpl extends AbstractDAO<Integer, Event> implements EventDAO {
    private static final int NBR_EVENTS_DISPLAY = 4;
    private static EventDAOImpl instance = null;

    private EventDAOImpl() {
        super();
    }

    public static EventDAOImpl getInstance() {
        if (instance == null) {
            instance = new EventDAOImpl();
        }
        return instance;

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

    @Override
    public List<Event> getPageEvents(User user, int pageNumber) {

        final List<Event> results = getEntityManagerService().performQuery(em -> {
            CriteriaBuilder cb = em.getCriteriaBuilder();

            CriteriaQuery<Event> cq = cb.createQuery(getEntityClass());
            Root<Event> root = cq.from(getEntityClass());

            cq.where(cb.or(cb.isNotMember(user, root.get(Event_.attendees))), cb.notEqual(root.get(Event_.creator), user));

            TypedQuery<Event> q = em.createQuery(cq);
            q.setFirstResult((pageNumber - 1) * NBR_EVENTS_DISPLAY);
            q.setMaxResults(NBR_EVENTS_DISPLAY);
            return q.getResultList();
        });

        return results;
    }

    @Override
    public List<Event> getPageEventsAnonymous(int pageNumber) {

        final List<Event> results = getEntityManagerService().performQuery(em -> {
            CriteriaBuilder cb = em.getCriteriaBuilder();

            CriteriaQuery<Event> cq = cb.createQuery(getEntityClass());
            Root<Event> root = cq.from(getEntityClass());

            TypedQuery<Event> q = em.createQuery(cq);
            q.setFirstResult((pageNumber - 1) * NBR_EVENTS_DISPLAY);
            q.setMaxResults(NBR_EVENTS_DISPLAY);
            return q.getResultList();
        });

        return results;
    }


    @Override
    public List<Event> getCreatorPageEvents(User creator, int pageNumber) throws MailNotFoundException {

        final List<Event> results = getEntityManagerService().performQuery(em -> {
            CriteriaBuilder cb = em.getCriteriaBuilder();

            CriteriaQuery<Event> cq = cb.createQuery(getEntityClass());
            Root<Event> root = cq.from(getEntityClass());

            cq.where(cb.or(cb.equal(root.get(Event_.creator), creator), cb.isMember(creator, root.get(Event_.attendees))));

            TypedQuery<Event> q = em.createQuery(cq);
            q.setFirstResult((pageNumber - 1) * NBR_EVENTS_DISPLAY);
            q.setMaxResults(NBR_EVENTS_DISPLAY);
            return q.getResultList();
        });

        return results;
    }

    @Override
    public Event findEventByID(Integer ID) {

        final List<Event> result = getEntityManagerService().performQuery(em -> {
            CriteriaBuilder cb = em.getCriteriaBuilder();

            CriteriaQuery<Event> cq = cb.createQuery(getEntityClass());
            Root<Event> root = cq.from(getEntityClass());

            cq.where(cb.equal(root.get(Event_.id), ID));

            TypedQuery<Event> q = em.createQuery(cq);
            return q.getResultList();
        });

        return result.get(0);
    }

    @Override
    public void deleteEvent(Integer id) {

        getEntityManagerService().performQuery(em -> {

            Event event = findEventByID(id);

            em.getTransaction().begin();
            em.remove(event);
            em.getTransaction().commit();

            return null;
        });

    }

    @Override
    public void updateEvent(Event event, Integer id) {

        getEntityManagerService().performQuery(em -> {

            Event eventToUpdate = findEventByID(id);

            em.getTransaction().begin();
            eventToUpdate.setDescription(event.getDescription());
            eventToUpdate.setLocation(event.getLocation());
            eventToUpdate.setVisible(event.isVisible());
            eventToUpdate.setDateBegin(event.getDateBegin());
            eventToUpdate.setDateEnd(event.getDateEnd());
            eventToUpdate.setTimeBegin(event.getTimeBegin());
            eventToUpdate.setTimeEnd(event.getTimeEnd());
            em.getTransaction().commit();

            return null;
        });

    }
}
