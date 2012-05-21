package problemofdrunks.game.exeption;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 16.05.12
 * Time: 14:56
 * To change this template use File | Settings | File Templates.
 */
public class GameBuilderException extends Exception {
    public GameBuilderException() {}
    public GameBuilderException(String message) {
        super(message);
    }
    public GameBuilderException(String message, Throwable cause) {
        super(message, cause);
    }
}