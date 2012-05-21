package problemofdrunks.field.exception;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 15.04.12
 * Time: 19:41
 * To change this template use File | Settings | File Templates.
 */
public class NotEmptyCellException extends CoordinateException {
    public NotEmptyCellException() {}
    public NotEmptyCellException(String message) {
        super(message);
    }
    public NotEmptyCellException(String message, Throwable cause) {
        super(message, cause);
    }
}
