package problemofdrunks.field.exception;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 21.05.12
 * Time: 22:27
 * To change this template use File | Settings | File Templates.
 */
public class PathFindException extends Exception{
    public PathFindException() {}
    public PathFindException(String message) {
        super(message);
    }
    public PathFindException(String message, Throwable cause) {
        super(message, cause);
    }
}
