package cz.cvut.fel.ear.rsp.travelandwork.exception;

public class NotAllowedException extends Exception{
    public NotAllowedException() {
        super("Forbidden operation.");
    }
    public NotAllowedException(String message) {
        super(message);
    }
}
