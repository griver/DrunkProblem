package problemofdrunks.game;

import problemofdrunks.field.IField;
import problemofdrunks.objects.IGameObject;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 14.03.12
 * Time: 13:15
 * To change this template use File | Settings | File Templates.
 */
public interface IGame {
    IField getField();
    void setField(IField field);
    void registerGameObject(IGameObject obj);
    void removeGameObject(IGameObject obj);
    boolean nextStep() throws GameStepException;
}
