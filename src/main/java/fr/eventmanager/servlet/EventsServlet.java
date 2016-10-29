package fr.eventmanager.servlet;

import fr.eventmanager.builder.EventBuilder;
import fr.eventmanager.dao.EventDAO;
import fr.eventmanager.dao.impl.EventDAOImpl;
import fr.eventmanager.model.Event;
import fr.eventmanager.model.User;
import fr.eventmanager.utils.HttpMethod;
import fr.eventmanager.utils.Route;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Event servlet to handle request to /events.
 */
public class EventsServlet extends Servlet {
    private EventDAO eventDAO;

    @Override
    public void init() throws ServletException {
        super.init(this);
        this.eventDAO = EventDAOImpl.getInstance();

        registerRoute(HttpMethod.GET, new Route(Pattern.compile("(/)?"), "getEvents", true));
        registerRoute(HttpMethod.GET, new Route(Pattern.compile("/\\d+"), "getEvent", true));
        registerRoute(HttpMethod.GET, new Route(Pattern.compile("/create"), "createEvent", true));
        registerRoute(HttpMethod.POST, new Route(Pattern.compile("/create"), "postEvent", true));
        registerRoute(HttpMethod.GET, new Route(Pattern.compile("(/myEvents)"), "getMyEvents", true));
        registerRoute(HttpMethod.GET, new Route(Pattern.compile("(/browse)"), "browseEvents", false));
    }

    public void getEvents(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("events", eventDAO.findAll());
        getServletContext().getRequestDispatcher("/WEB-INF/pages/eventsBrowse.jsp").forward(req, resp);
    }

    public void getEvent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final int eventID = Integer.parseInt(req.getPathInfo().split("/")[1]);
        final List<Event> event = new ArrayList<>();
        event.add(eventDAO.findById(eventID));

        req.setAttribute("events", event);
        getServletContext().getRequestDispatcher("/WEB-INF/pages/eventsBrowse.jsp").forward(req, resp);
    }

    public void createEvent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/pages/eventCreate.jsp").forward(req, resp);
    }

    public void postEvent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getSessionUser(req.getSession()).ifPresent(user -> {
            eventDAO.persist(
                    new EventBuilder(user)
                            .setLabel(req.getParameter("label"))
                            .setDescription(req.getParameter("description"))
                            .setLocation(req.getParameter("location"))
                            .setVisible("on".equalsIgnoreCase(req.getParameter("visible")))
                            .build()
            );
        });

        resp.sendRedirect(getServletContext().getContextPath() + "/events");
    }

    public void getMyEvents(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/pages/myEvents.jsp").forward(req, resp);
    }

    public void browseEvents(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/pages/eventsBrowse.jsp").forward(req, resp);
    }
}
