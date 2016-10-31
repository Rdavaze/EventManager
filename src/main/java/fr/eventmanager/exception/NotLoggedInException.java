package fr.eventmanager.exception;

/**
 * Exception for when a user tries to access internal route, not being loggedIn/
 */
public class NotLoggedInException extends Exception {
    public NotLoggedInException() {
        super();
    }

    public NotLoggedInException(String s) {
        super(s);
    }
}
