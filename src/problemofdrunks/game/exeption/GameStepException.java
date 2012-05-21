package problemofdrunks.game.exeption;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 16.05.12
 * Time: 15:50
 * To change this template use File | Settings | File Templates.
 */
public class GameStepException extends Exception{
    public GameStepException() {}
    public GameStepException(String message) {
        super(message);
    }
    public GameStepException(String message, Throwable cause) {
        super(message, cause);
    }
}
