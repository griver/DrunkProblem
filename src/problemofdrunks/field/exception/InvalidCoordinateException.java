package problemofdrunks.field.exception;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 15.04.12
 * Time: 19:33
 * To change this template use File | Settings | File Templates.
 */
public class InvalidCoordinateException extends CoordinateException {
    public InvalidCoordinateException() {}
    public InvalidCoordinateException(String message) {
        super(message);
    }
    public InvalidCoordinateException(String message, Throwable cause) {
        super(message, cause);
    }
}
