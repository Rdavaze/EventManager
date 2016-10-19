package fr.eventmanager.controller;

import fr.eventmanager.utils.HttpMethod;
import fr.eventmanager.utils.Route;
import fr.eventmanager.utils.ServletRouter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Event controller to handle request to /events.
 */
public class EventsServlet extends Servlet {
    @Override
    public void init() {
        super.router = new ServletRouter(this);
        super.router.registerRoute(HttpMethod.GET, new Route(Pattern.compile("(/)?"), "getEvents"));
        super.router.registerRoute(HttpMethod.GET, new Route(Pattern.compile("/\\d+"), "getEvent"));
    }

    @Override
    String getContextPath() {
        return "/events";
    }

    public void getEvents(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("Salut c'est LES événementS !");
    }

    public void getEvent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write(String.format("Salut c'est l'événement %s !", req.getPathInfo().split("/")[1]));
    }
}
