package fr.eventmanager.servlet;

import fr.eventmanager.exception.NotLoggedInException;
import fr.eventmanager.model.User;
import fr.eventmanager.utils.HttpMethod;
import fr.eventmanager.utils.Route;
import fr.eventmanager.utils.ServletRouter;
import fr.eventmanager.utils.UserSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * Abstract servlet to generify application servlets.
 */
public abstract class Servlet extends HttpServlet implements UserSession {
    private ServletRouter router;

    void init(Servlet servlet) throws ServletException {
        super.init();
        this.router = new ServletRouter(servlet);
    }

    void registerRoute(HttpMethod method, Route getEvents) {
        this.router.registerRoute(method, getEvents);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.process(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.process(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            this.router.process(req, resp);
        } catch (NotLoggedInException e) {
            resp.sendRedirect(this.getServletContext().getContextPath() + "/login");
        }
    }

    @Override
    public Optional<User> getSessionUser(HttpSession session) {
        return Optional.ofNullable((User) session.getAttribute(USER_ATTR_NAME));
    }

    @Override
    public void setSessionUser(HttpSession session, User user) {
        session.setAttribute(USER_ATTR_NAME, user);
    }

    @Override
    public Boolean isSessionLogged(HttpSession session) {
        Boolean logged = (Boolean) session.getAttribute(LOGGED_ATTR_NAME);
        if (logged == null) {
            logged = false;
            setSessionLogged(session, false);
        }
        return logged;
    }

    @Override
    public void setSessionLogged(HttpSession session, Boolean logged) {
        session.setAttribute(LOGGED_ATTR_NAME, logged);
    }
}
