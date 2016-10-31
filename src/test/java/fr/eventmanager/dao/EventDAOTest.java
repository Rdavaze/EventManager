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
    public void testFindAll() {
        final List<Event> events = eventDAO.findAll();
        assertEquals(events.size(), 11);
    }

    @Test
    public void testFindLastEvents() {

        List<Event> listTwoEvents = eventDAO.findLastEvents(2);
        List<Event> listTenEvents = eventDAO.findLastEvents(10);

        assertEquals(listTwoEvents.size(), 2);
        assertEquals(listTenEvents.size(), 10);

    }


    @Test
    public void testGetPageEvents() {

        List<Event> eventsFirstPage = eventDAO.getPageEvents(1);
        List<Event> eventsSecondPage = eventDAO.getPageEvents(2);
        List<Event> eventsThirdPage = eventDAO.getPageEvents(3);
        List<Event> eventsFourthPage = eventDAO.getPageEvents(4);

        assertEquals(eventsFirstPage.size(), 4);
        assertEquals(eventsFirstPage.get(0).getId(), new Integer(6));
        assertEquals(eventsFirstPage.get(3).getId(), new Integer(9));

        assertEquals(eventsSecondPage.size(), 4);
        assertEquals(eventsSecondPage.get(0).getId(), new Integer(10));
        assertEquals(eventsSecondPage.get(3).getId(), new Integer(13));

        assertEquals(eventsThirdPage.size(), 3);
        assertEquals(eventsThirdPage.get(0).getId(), new Integer(14));
        assertEquals(eventsThirdPage.get(2).getId(), new Integer(16));

        assertEquals(eventsFourthPage.size(), 0);


    }

}
