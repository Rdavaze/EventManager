package fr.eventmanager.utils;

import java.util.regex.Pattern;

/**
 * Route POJO class to store route data for servlet.
 */
public class Route {
    private final Pattern pattern;
    private final String handler;
    private final Boolean internal;

    public Route(Pattern pattern, String handler, boolean internal) {
        this.pattern = pattern;
        this.handler = handler;
        this.internal = internal;
    }

    public boolean matches(String path) {
        if (path != null) {
            return this.pattern.matcher(path).matches();
        }
        return false;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public String getHandler() {
        return handler;
    }

    public Boolean isInternal() {
        return internal;
    }
}
