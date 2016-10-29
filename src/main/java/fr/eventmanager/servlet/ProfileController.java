package fr.eventmanager.servlet;

import fr.eventmanager.builder.UserBuilder;
import fr.eventmanager.dao.UserDAO;
import fr.eventmanager.dao.impl.UserDAOImpl;
import fr.eventmanager.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Controller for the profile page
 */
public class ProfileController extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userDAO = UserDAOImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        updateProfile(req);

        getServletContext().getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(req, resp);
    }

    private void updateProfile(HttpServletRequest req) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        User currentUser = (User) session.getAttribute("user");

        User newUser = new UserBuilder()
                .setEmail(req.getParameter("email"))
                .setNom(req.getParameter("name"))
                .setPrenom(req.getParameter("firstname"))
                .setPassword(req.getParameter("password"))
                .build();


        this.userDAO.updateUserInfo(currentUser.getId(), newUser);

        session.setAttribute("user", newUser);

    }

}
