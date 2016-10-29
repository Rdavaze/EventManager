package fr.eventmanager.exception;

/**
 * Exception for when a given password is invalid.
 */
public class WrongPasswordException extends Exception {
    public WrongPasswordException() {
        super();
    }

    public WrongPasswordException(String s) {
        super(s);
    }
}
