package fr.eventmanager.builder;

import fr.eventmanager.model.Event;
import fr.eventmanager.model.User;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Romain on 30/10/2016.
 */
public class EventBuilderTest {

    private static Event event;
    private static User johnDoe;

    @BeforeClass
    public static void setUpClass() {
        johnDoe = new User("john.doe@gmail.com", "password", "John", "Doe", null);
        event = new Event(johnDoe, "Premier", "Ceci est un event", null, null, "Nantes", true, null);
    }

    @Test
    public void testEventBuilder() {

        Event testEvent = new EventBuilder(johnDoe)
                .setDescription("Ceci est un event")
                .setLabel("Premier")
                .setLocation("Nantes")
                .build();

        assertEquals(event.getCreator(), testEvent.getCreator());
        assertEquals(event.getLabel(), testEvent.getLabel());
        assertEquals(event.getLocation(), testEvent.getLocation());
        assertEquals(event.getDescription(), testEvent.getDescription());

    }


}
