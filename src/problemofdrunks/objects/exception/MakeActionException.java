package problemofdrunks.objects.exception;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 16.05.12
 * Time: 15:23
 * To change this template use File | Settings | File Templates.
 */
public class MakeActionException  extends Exception  {
    public MakeActionException() {}
    public MakeActionException(String message) {
        super(message);
    }
    public MakeActionException(String message, Throwable cause) {
        super(message, cause);
    }
}
