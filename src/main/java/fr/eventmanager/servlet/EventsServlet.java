package fr.eventmanager.servlet;

import fr.eventmanager.dao.EventManager;
import fr.eventmanager.model.Event;
import fr.eventmanager.utils.HttpMethod;
import fr.eventmanager.utils.Route;
import fr.eventmanager.utils.ServletRouter;

import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Event servlet to handle request to /events.
 */
public class EventsServlet extends Servlet {
    @Override
    public void init() {
        super.router = new ServletRouter(this);
        super.router.registerRoute(HttpMethod.GET, new Route(Pattern.compile("(/)?"), "getEvents"));
        super.router.registerRoute(HttpMethod.GET, new Route(Pattern.compile("/\\d+"), "getEvent"));

        EventManager.getInstance().populate();
    }

    public void getEvents(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("events", EventManager.getInstance().findAll());
        getServletContext().getRequestDispatcher("/WEB-INF/pages/events.jsp").forward(req, resp);
    }

    public void getEvent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final int eventID = Integer.parseInt(req.getPathInfo().split("/")[1]);
        final List<Event> event = new ArrayList<>();
        event.add(EventManager.getInstance().findEvent(eventID));

        req.setAttribute("events", event);
        getServletContext().getRequestDispatcher("/WEB-INF/pages/events.jsp").forward(req, resp);
    }
}
