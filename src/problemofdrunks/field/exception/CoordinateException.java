package problemofdrunks.field.exception;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 16.05.12
 * Time: 15:42
 * To change this template use File | Settings | File Templates.
 */
public class CoordinateException extends Exception{
    public CoordinateException() {}
    public CoordinateException(String message) {
        super(message);
    }
    public CoordinateException(String message, Throwable cause) {
        super(message, cause);
    }
}
