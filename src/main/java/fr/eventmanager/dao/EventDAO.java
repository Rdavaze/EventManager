package fr.eventmanager.dao;

import fr.eventmanager.exception.MailNotFoundException;
import fr.eventmanager.model.Event;
import fr.eventmanager.model.User;

import java.util.List;
import java.util.Set;

/**
 * Created by guillaume-chs on 21/10/16.
 */
public interface EventDAO extends DAO<Integer, Event> {
    String QUERY_EVENTS = "SELECT e FROM %s e";

    default String getQueryEvents(String className) {
        return String.format(QUERY_EVENTS, className);
    }

    List<Event> findLastEvents(int nbEvents);

    Set<User> getEventAttendees(Event event);

    Set<User> getEventAttendees(Integer id);

    List<Event> getPageEvents(User user, int pageNumber);

    List<Event> getPageEventsAnonymous(int pageNumber);

    List<Event> getCreatorPageEvents(User creator, int pageNumber) throws MailNotFoundException;

    void deleteEvent(Integer id);

    Event findEventByID(Integer id);

    void updateEvent(Event event, Integer id);
}
