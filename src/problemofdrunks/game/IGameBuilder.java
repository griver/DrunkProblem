package problemofdrunks.game;

import problemofdrunks.field.IField;
import problemofdrunks.game.exeption.GameBuilderException;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 07.04.12
 * Time: 19:34
 * To change this template use File | Settings | File Templates.
 */
public interface IGameBuilder {
    IGame buildGame() throws GameBuilderException;
    void setGame(IGame game);
    void setField(IField field);
}
