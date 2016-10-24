package fr.eventmanager.dao;


import fr.eventmanager.dao.impl.EventDAOImpl;
import fr.eventmanager.model.Event;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class EventDAOTest {
    private static EventDAO eventDAO;

    @BeforeClass
    public static void setUpClass() {
        eventDAO = EventDAOImpl.getInstance();
    }

    @Test
    public void testEventDAOImpl() {
        final List<Event> events = eventDAO.findAll();
        assertEquals(events.size(), 3);
    }
}
