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

    private static User romain;

    @BeforeClass
    public static void setUpClass() {

        userDAO = UserDAOImpl.getInstance();

        romain = new UserBuilder("romain.davaze@gmail.com", "password", "Romain", "Davaze")
                .setCompany("Sigma")
                .build();
    }

    @Test
    public void testFindAll() {
        final List<User> users = userDAO.findAll();
        assertEquals(users.size(), 5);
    }

    @Test
    public void testFindByCredentials() throws MailNotFoundException, WrongPasswordException {

        User userFoundOnlyMail = userDAO.findByCredentials("romain.davaze@gmail.com");
        assertEquals(romain.getEmail(), userFoundOnlyMail.getEmail());
        assertEquals(romain.getFirstname(), userFoundOnlyMail.getFirstname());
        assertEquals(romain.getLastname(), userFoundOnlyMail.getLastname());
        assertEquals(romain.getPassword(), userFoundOnlyMail.getPassword());


        User userFoundMailAndPassword = userDAO.findByCredentials("romain.davaze@gmail.com", "password");
        assertEquals(romain.getEmail(), userFoundMailAndPassword.getEmail());
        assertEquals(romain.getFirstname(), userFoundMailAndPassword.getFirstname());
        assertEquals(romain.getLastname(), userFoundMailAndPassword.getLastname());
        assertEquals(romain.getPassword(), userFoundMailAndPassword.getPassword());

    }


    @Test
    public void testUpdateUser() throws MailNotFoundException {

        User newRomain = romain;
        newRomain.setFirstname("Roro");
        newRomain.setLastname("Dada");

        userDAO.updateUser(userDAO.findByCredentials("romain.davaze@gmail.com").getId(), newRomain);

        User userFound = userDAO.findByCredentials("romain.davaze@gmail.com");
        assertEquals(newRomain.getEmail(), userFound.getEmail());
        assertEquals(newRomain.getFirstname(), userFound.getFirstname());

    }


    @Test
    public void testEmailExists() {

        String wrongMail = "toto@toto.fr";

        assertEquals(userDAO.emailExists(romain.getEmail()), true);
        assertEquals(userDAO.emailExists(wrongMail), false);
    }


    @Test
    public void testPasswordExists() {

        String wrongPassword = "titi";

        assertEquals(userDAO.passwordExists(romain.getPassword()), true);
        assertEquals(userDAO.passwordExists(wrongPassword), false);
    }


    @Test
    public void testUpdatePassword() throws MailNotFoundException {

        User newJohnDoe = romain;
        newJohnDoe.setPassword("bonjour");

        userDAO.updatePassword(newJohnDoe.getEmail(), newJohnDoe.getPassword());

        User userFound = userDAO.findByCredentials("romain.davaze@gmail.com");
        assertEquals(newJohnDoe.getPassword(), userFound.getPassword());

    }

}
