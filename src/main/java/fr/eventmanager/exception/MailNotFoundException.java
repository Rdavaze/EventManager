package fr.eventmanager.exception;

/**
 * Exception for when a given mail does not exist.
 */
public class MailNotFoundException extends Exception {
    public MailNotFoundException() {
        super();
    }

    public MailNotFoundException(String s) {
        super(s);
    }
}
