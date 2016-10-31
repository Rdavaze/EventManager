package fr.eventmanager.exception;

import fr.eventmanager.builder.UserBuilder;
import fr.eventmanager.dao.UserDAO;
import fr.eventmanager.dao.impl.UserDAOImpl;
import fr.eventmanager.model.User;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Romain on 30/10/2016.
 */
public class ExceptionTest {
    private static UserDAO userDAO;

    private static User romain;

    @BeforeClass
    public static void setUpClass() {
        userDAO = UserDAOImpl.getInstance();

        romain = new UserBuilder("r)omain.davaze@gmail.com", "password", "Romain", "Davaze")
                .setCompany("Sigma")
                .build();
    }

    @Test(expected = MailNotFoundException.class)
    public void testMailNotFound() throws MailNotFoundException {

        String wrongMail = "aaa@aaa.com";
        userDAO.findByCredentials(wrongMail);
    }

    @Test(expected = WrongPasswordException.class)
    public void testWrongPassword() throws MailNotFoundException, WrongPasswordException {

        String wrongPassword = "toto";
        String email = "romain.davaze@gmail.com";

        userDAO.findByCredentials(email, wrongPassword);

    }

}
