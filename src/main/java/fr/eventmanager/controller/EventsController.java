package fr.eventmanager.controller;

import fr.eventmanager.utils.HttpMethod;
import fr.eventmanager.utils.Route;
import fr.eventmanager.utils.ServletRouter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Login controller to handle request to /login.
 */
public class EventsController extends HttpServlet {
    private ServletRouter router;

    @Override
    public void init() throws ServletException {
        super.init();

        this.router = new ServletRouter(this);
        this.router.registerRoute(HttpMethod.GET, new Route(Pattern.compile("(/)?"), "getEvents"));
        this.router.registerRoute(HttpMethod.GET, new Route(Pattern.compile("/\\d+"), "getEvent"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.router.process(req, resp);
    }

    public void getEvents(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("Salut c'est LES événementS !");
    }

    public void getEvent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write(String.format("Salut c'est l'événement %s !", req.getPathInfo().split("/")[1]));
    }
}
