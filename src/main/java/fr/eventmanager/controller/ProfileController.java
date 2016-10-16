package fr.eventmanager.controller;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        setUserInfo(req);

        getServletContext().getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(req, resp);
    }

    /**
     * Retrieve current user's info and display it in the page
     *
     * @param req request
     */
    private void setUserInfo(HttpServletRequest req) {

        HttpSession session = req.getSession(false);

        // TODO : retrieve user's info in the backend


        req.setAttribute("profileFirstName", "John");
        req.setAttribute("profileName", "Doe");
        req.setAttribute("profileMail", "johndoe@gmail.com");
        req.setAttribute("profilePassword", "password");
    }

}
