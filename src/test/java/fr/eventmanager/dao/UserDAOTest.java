package fr.eventmanager.dao;

import fr.eventmanager.builder.UserBuilder;
import fr.eventmanager.dao.impl.UserDAOImpl;
import fr.eventmanager.exception.MailNotFoundException;
import fr.eventmanager.exception.WrongPasswordException;
import fr.eventmanager.model.User;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserDAOTest {
    private static UserDAO userDAO;

    private static User johnDoe;

    @BeforeClass
    public static void setUpClass() {

        userDAO = UserDAOImpl.getInstance();

        johnDoe = new UserBuilder("john.doe@gmail.com", "password", "John", "Doe")
                .setCompany("OpenJDK")
                .build();
    }

    @Test
    public void testFindAll() {
        final List<User> users = userDAO.findAll();
        assertEquals(users.size(), 5);
    }

    @Test
    public void testFindByCredentials() throws MailNotFoundException, WrongPasswordException {

        User userFoundOnlyMail = userDAO.findByCredentials("john.doe@gmail.com");
        assertEquals(johnDoe.getEmail(), userFoundOnlyMail.getEmail());
        assertEquals(johnDoe.getFirstname(), userFoundOnlyMail.getFirstname());
        assertEquals(johnDoe.getLastname(), userFoundOnlyMail.getLastname());
        assertEquals(johnDoe.getPassword(), userFoundOnlyMail.getPassword());


        User userFoundMailAndPassword = userDAO.findByCredentials("john.doe@gmail.com", "password");
        assertEquals(johnDoe.getEmail(), userFoundMailAndPassword.getEmail());
        assertEquals(johnDoe.getFirstname(), userFoundMailAndPassword.getFirstname());
        assertEquals(johnDoe.getLastname(), userFoundMailAndPassword.getLastname());
        assertEquals(johnDoe.getPassword(), userFoundMailAndPassword.getPassword());

    }


    @Test
    public void testUpdateUser() throws MailNotFoundException {

        User newJohnDoe = johnDoe;
        newJohnDoe.setFirstname("Johnny");
        newJohnDoe.setLastname("Doeee");

        userDAO.updateUser(userDAO.findByCredentials("john.doe@gmail.com").getId(), newJohnDoe);

        User userFound = userDAO.findByCredentials("john.doe@gmail.com");
        assertEquals(newJohnDoe.getEmail(), userFound.getEmail());
        assertEquals(newJohnDoe.getFirstname(), userFound.getFirstname());

    }


    @Test
    public void testEmailExists() {

        String wrongMail = "toto@toto.fr";

        assertEquals(userDAO.emailExists(johnDoe.getEmail()), true);
        assertEquals(userDAO.emailExists(wrongMail), false);
    }


    @Test
    public void testPasswordExists() {

        String wrongPassword = "titi";

        assertEquals(userDAO.passwordExists(johnDoe.getPassword()), true);
        assertEquals(userDAO.passwordExists(wrongPassword), false);
    }


    @Test
    public void testUpdatePassword() throws MailNotFoundException {

        User newJohnDoe = johnDoe;
        newJohnDoe.setPassword("bonjour");

        userDAO.updatePassword(newJohnDoe.getEmail(), newJohnDoe.getPassword());

        User userFound = userDAO.findByCredentials("john.doe@gmail.com");
        assertEquals(newJohnDoe.getPassword(), userFound.getPassword());

    }

}
