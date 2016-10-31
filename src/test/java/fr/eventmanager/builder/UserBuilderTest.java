package fr.eventmanager.builder;

import fr.eventmanager.model.User;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Romain on 30/10/2016.
 */
public class UserBuilderTest {

    private static User johnDoe;

    @BeforeClass
    public static void setUpClass() {
        johnDoe = new User("john.doe@gmail.com", "password", "John", "Doe", "OpenJDK", null);
    }

    @Test
    public void testUserBuilder() {
        User userTest = new UserBuilder("john.doe@gmail.com", "password", "John", "Doe")
                .setCompany("OpenJDK")
                .build();

        assertEquals(johnDoe.getPassword(), userTest.getPassword());
        assertEquals(johnDoe.getLastname(), userTest.getLastname());
        assertEquals(johnDoe.getFirstname(), userTest.getFirstname());
        assertEquals(johnDoe.getEmail(), userTest.getEmail());

    }


}
