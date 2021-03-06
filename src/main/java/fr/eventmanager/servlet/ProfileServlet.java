package fr.eventmanager.servlet;

import fr.eventmanager.builder.UserBuilder;
import fr.eventmanager.dao.UserDAO;
import fr.eventmanager.dao.impl.UserDAOImpl;
import fr.eventmanager.model.User;
import fr.eventmanager.utils.HttpMethod;
import fr.eventmanager.utils.Route;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Controller for the profile page
 */
public class ProfileServlet extends Servlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        super.init(this);
        this.userDAO = UserDAOImpl.getInstance();

        registerRoute(HttpMethod.GET, new Route(Pattern.compile("(/)?"), "getProfile", true));
        registerRoute(HttpMethod.POST, new Route(Pattern.compile("(/update)?"), "updateProfile", true));
    }

    public void getProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(req, resp);
    }

    public void updateProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (this.processUpdate(req)) {
            resp.sendRedirect(this.getServletContext().getContextPath() + "/profile");
        } else {
            resp.sendRedirect(this.getServletContext().getContextPath() + "/login");
        }
    }

    private boolean processUpdate(HttpServletRequest req) throws ServletException, IOException {
        getSessionUser(req.getSession()).ifPresent(currUser -> {
            final User newUser = new UserBuilder(
                    req.getParameter("email"),
                    req.getParameter("password"),
                    req.getParameter("firstname"),
                    req.getParameter("lastname")
            ).setCompany(req.getParameter("company")).build();

            this.userDAO.updateUser(currUser.getId(), newUser);
            setSessionUser(req.getSession(), newUser);
        });
        return true;
    }
}
