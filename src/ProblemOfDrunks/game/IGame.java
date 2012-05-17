package ProblemOfDrunks.game;

import ProblemOfDrunks.game.exeption.GameStepException;
import ProblemOfDrunks.field.IField;
import ProblemOfDrunks.objects.IGameObject;

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
    void registerActiveObject(IGameObject obj);
    void removeActiveObject(IGameObject obj);
    boolean nextStep() throws GameStepException;
}
