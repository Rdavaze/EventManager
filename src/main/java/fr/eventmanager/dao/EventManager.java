package fr.eventmanager.dao;

import fr.eventmanager.model.Event;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 * EventManager DAO to manage Event entities.
 */
public class EventManager {
    private static EventManager instance = null;
    private final EntityManagerFactory emf;

    private EventManager() {
        this.emf = Persistence.createEntityManagerFactory("EventManager");
    }

    public static EventManager getInstance() {
        if (EventManager.instance == null) {
            EventManager.instance = new EventManager();
        }
        return EventManager.instance;
    }

    public Event findEvent(int id) {
        final EntityManager em = this.emf.createEntityManager();
        final Event found = em.find(Event.class, id);
        em.close();

        return found;
    }

    public List<Event> findAll() {
        final EntityManager em = this.emf.createEntityManager();
        final List<Event> results = em.createQuery("SELECT e FROM Event e", Event.class).getResultList();
        em.close();

        return results;
    }

    public void populate() {
        final EntityManager em = this.emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Event("Un premier événement"));
        em.persist(new Event("Evénement numéro 2"));
        em.persist(new Event("Troisième événement"));
        em.getTransaction().commit();
        em.close();
    }
}
