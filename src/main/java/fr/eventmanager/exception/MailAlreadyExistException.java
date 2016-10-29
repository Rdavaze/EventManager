package fr.eventmanager.exception;

/**
 * Exception for when a given mail already exists in the database (e.g. when registering again).
 */
public class MailAlreadyExistException extends Exception {
    public MailAlreadyExistException() {
        super();
    }

    public MailAlreadyExistException(String s) {
        super(s);
    }
}
