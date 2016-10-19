package fr.eventmanager.utils;

import fr.eventmanager.servlet.Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Abstract top-layer servlet.
 */
public class ServletRouter {
    private Servlet servlet;
    private final Map<HttpMethod, List<Route>> routes;

    public ServletRouter(Servlet servlet) {
        this.servlet = servlet;
        this.routes = new HashMap<>();
    }

    public void registerRoute(HttpMethod method, Route route) {
        if (this.routes.containsKey(method)) {
            this.routes.get(method).add(route);
        } else {
            final List<Route> methodRoutes = new ArrayList<>();
            methodRoutes.add(route);
            this.routes.put(method, methodRoutes);
        }
    }

    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Optional<HttpMethod> methodOptional = HttpMethod.getHttpMethod(req.getMethod());
        if (!methodOptional.isPresent()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        final String path = (req.getPathInfo() != null) ? req.getPathInfo() : "/";

        final Optional<Route> routeOptional = this.routes.get(methodOptional.get()).stream()
                .filter(r -> r.matches(path))
                .findFirst();

        if (routeOptional.isPresent()) {
            try {
                final String methodName = routeOptional.get().getHandler();
                final Class<?>[] classes = {HttpServletRequest.class, HttpServletResponse.class};
                final Object[] args = {req, resp};

                final Method m = servlet.getClass().getDeclaredMethod(methodName, classes);
                m.setAccessible(true);
                m.invoke(servlet, args);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Putin");
        }
    }
}
