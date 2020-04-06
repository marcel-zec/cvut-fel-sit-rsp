package cz.cvut.fel.rsp.travelandwork.exception;

public class BadDateException extends Exception {

    public BadDateException() {
        super("Incorrect date.");
    }

    public BadDateException(String message) {
        super(message);
    }


}
