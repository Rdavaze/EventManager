package fr.eventmanager.servlet;

import fr.eventmanager.builder.EventBuilder;
import fr.eventmanager.dao.EventDAO;
import fr.eventmanager.dao.UserDAO;
import fr.eventmanager.dao.impl.EventDAOImpl;
import fr.eventmanager.dao.impl.UserDAOImpl;
import fr.eventmanager.model.Event;
import fr.eventmanager.model.User;
import fr.eventmanager.utils.HttpMethod;
import fr.eventmanager.utils.Route;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Pattern;

import static fr.eventmanager.utils.DateUtil.getMonthIndex;

/**
 * Event servlet to handle request to /events.
 */
public class EventsServlet extends Servlet {
    private EventDAO eventDAO;
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        super.init(this);
        this.eventDAO = EventDAOImpl.getInstance();
        this.userDAO = UserDAOImpl.getInstance();

        registerRoute(HttpMethod.GET, new Route(Pattern.compile("/\\d+"), "getEvent", false));
        registerRoute(HttpMethod.GET, new Route(Pattern.compile("/create"), "createEvent", true));
        registerRoute(HttpMethod.GET, new Route(Pattern.compile("/myEvents"), "getMyEvents", true));
        registerRoute(HttpMethod.GET, new Route(Pattern.compile("/browse"), "browseEvents", false));
        registerRoute(HttpMethod.GET, new Route(Pattern.compile("/subscribe/\\d+"), "subscribe", true));
        registerRoute(HttpMethod.GET, new Route(Pattern.compile("/unsubscribe/\\d+"), "unsubscribe", true));
        registerRoute(HttpMethod.GET, new Route(Pattern.compile("/\\d+/delete"), "deleteEvent", true));
        registerRoute(HttpMethod.POST, new Route(Pattern.compile("/create"), "postEvent", true));
    }

    public void getEvent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final int eventID = Integer.parseInt(req.getPathInfo().split("/")[1]);
        final Event event = eventDAO.findById(eventID);

        req.setAttribute("event", event);
        getServletContext().getRequestDispatcher("/WEB-INF/pages/eventDetail.jsp").forward(req, resp);
    }

    public void createEvent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/pages/eventCreate.jsp").forward(req, resp);
    }

    public void getMyEvents(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final int index = parseEventIndex(req);
        req.setAttribute("events", eventDAO.getPageEvents(index));
        getServletContext().getRequestDispatcher("/WEB-INF/pages/myEvents.jsp").forward(req, resp);
    }

    public void browseEvents(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final int index = parseEventIndex(req);
        req.setAttribute("events", eventDAO.getPageEvents(index));
        getServletContext().getRequestDispatcher("/WEB-INF/pages/eventsBrowse.jsp").forward(req, resp);
    }

    public void subscribe(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Optional<User> userOptional = getSessionUser(req.getSession());
        if (userOptional.isPresent()) {
            final int eventID = Integer.parseInt(req.getPathInfo().split("/")[2]);
            final Event event = eventDAO.findById(eventID);
            userDAO.subscribeTo(userOptional.get(), event);
            resp.sendRedirect(getServletContext().getContextPath() + "/events/" + event.getId());
        } else {
            resp.sendRedirect(getServletContext().getContextPath() + req.getPathInfo());
        }
    }

    public void unsubscribe(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Optional<User> userOptional = getSessionUser(req.getSession());
        if (userOptional.isPresent()) {
            final int eventID = Integer.parseInt(req.getPathInfo().split("/")[2]);
            final Event event = eventDAO.findById(eventID);
            userDAO.unsubscribeFrom(userOptional.get(), event);
        }
        resp.sendRedirect(getServletContext().getContextPath() + "/events/browse");
    }

    public void postEvent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String label = req.getParameter("label");
        final String description = req.getParameter("description");
        final String location = req.getParameter("location");
        final boolean visible = "on".equalsIgnoreCase(req.getParameter("visible"));

        final Optional<Date> dateBeginOpt = parseDateTime(req.getParameter("date-begin"), req.getParameter("time-begin"));
        final Optional<Date> dateEndOpt = parseDateTime(req.getParameter("date-begin"), req.getParameter("time-begin"));

        getSessionUser(req.getSession()).ifPresent(user -> {
            EventBuilder eventBuilder = new EventBuilder(label, user)
                    .setDescription(description)
                    .setLocation(location)
                    .setVisible(visible);
            dateBeginOpt.ifPresent(eventBuilder::setDateBegin);
            dateEndOpt.ifPresent(eventBuilder::setDateEnd);

            eventDAO.persist(eventBuilder.build());
        });

        resp.sendRedirect(getServletContext().getContextPath() + "/events/browse");
    }

    private int parseEventIndex(HttpServletRequest req) {
        Optional<Integer> indexOptional;
        try {
            indexOptional = Optional.ofNullable(Integer.parseInt(req.getParameter("index")));
        } catch (NumberFormatException e) {
            indexOptional = Optional.empty();
        }
        return indexOptional.isPresent() ? indexOptional.get() : 1;
    }

    public void deleteEvent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String eventID = req.getPathInfo().split("/")[1];

        eventID = eventID.split("/")[0];

        int id = Integer.parseInt(eventID);

        eventDAO.deleteEvent(id);

        resp.sendRedirect(this.getServletContext().getContextPath() + "/events/myEvents");
    }

    private Optional<Date> parseDateTime(String date, String time) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        try {
            final String[] dSplit = date.split(" ");
            final Date parsed = dateFormat.parse(dSplit[0] + getMonthIndex(dSplit[1]) + dSplit[2] + " " + time);
            return Optional.of(parsed);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
