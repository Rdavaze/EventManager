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
        johnDoe = new UserBuilder("john.doe@gmail.com", "password", "John", "Doe")
                .setCompany("OpenJDK")
                .build();

        event = new EventBuilder("Premier", johnDoe)
                .setDescription("Ceci est un event")
                .setLocation("Nantes")
                .setVisible(true)
                .build();
    }

    @Test
    public void testEventBuilder() {

        Event testEvent = new EventBuilder("Premier", johnDoe)
                .setDescription("Ceci est un event")
                .setLocation("Nantes")
                .build();

        assertEquals(event.getCreator(), testEvent.getCreator());
        assertEquals(event.getLabel(), testEvent.getLabel());
        assertEquals(event.getLocation(), testEvent.getLocation());
        assertEquals(event.getDescription(), testEvent.getDescription());

    }


}
