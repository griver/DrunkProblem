package problemofdrunks.field.exception;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 21.05.12
 * Time: 22:23
 * To change this template use File | Settings | File Templates.
 */
public class InvalidPathArgumentException extends PathFindException{
    public InvalidPathArgumentException(){}
    public InvalidPathArgumentException(String message){
        super(message);
    }
    public InvalidPathArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
