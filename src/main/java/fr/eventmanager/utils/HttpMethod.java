package fr.eventmanager.utils;

import java.util.Optional;

/**
 * Basic HttpMethods enumeration. With GET, POST, PUT, DELETE.
 */
public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");

    private final String name;

    HttpMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Optional<HttpMethod> getHttpMethod(String method) {
        method = method.toUpperCase();

        for (HttpMethod m : HttpMethod.values()) {
            if (m.getName().equals(method)) {
                return Optional.of(m);
            }
        }
        return Optional.empty();
    }
}
