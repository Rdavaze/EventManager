package fr.eventmanager.dao;

import fr.eventmanager.dao.impl.UserDAOImpl;
import fr.eventmanager.model.User;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserDAOTest {
    private static UserDAOImpl userDAO;

    @BeforeClass
    public static void setUpClass() {
        userDAO = UserDAOImpl.getInstance();
    }

    @Test
    public void testEventDAOImpl() {
        final List<User> users = userDAO.findAll();
        assertEquals(users.size(), 2);
    }
}
