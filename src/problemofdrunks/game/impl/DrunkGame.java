package problemofdrunks.game.impl;

import problemofdrunks.game.exeption.GameStepException;
import problemofdrunks.game.IGame;
import problemofdrunks.objects.IGameObject;
import problemofdrunks.objects.exception.MakeActionException;
import problemofdrunks.field.IField;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 07.04.12
 * Time: 19:41
 * To change this template use File | Settings | File Templates.
 */
public class DrunkGame implements IGame {
    //===Fields==========================================================
    private IField field;
    private HashSet<IGameObject> activeObjects = new HashSet<IGameObject>();
    private LinkedList<IGameObject> addedObjects = new LinkedList<IGameObject>();
    private LinkedList<IGameObject> removedObjects = new LinkedList<IGameObject>();
    //===/Fields=========================================================

    //===Constructors====================================================
    public DrunkGame() {}
    //===/Constructors===================================================

    //===Methods=========================================================
    @Override
    public void registerActiveObject(IGameObject object) {
        addedObjects.addLast(object);
    }

    @Override
    public void removeActiveObject(IGameObject object) {
        removedObjects.addLast(object);
    }

    @Override
    public boolean nextStep() throws GameStepException {
        try {
            activeObjects.removeAll(removedObjects);
            activeObjects.addAll(addedObjects);

            removedObjects.clear();
            addedObjects.clear();

            if(activeObjects.isEmpty())
                return false;

            for(IGameObject object : activeObjects) {
                object.makeAction();
            }
        } catch (MakeActionException e) {
            throw new GameStepException("Exception in one of GameObjects", e);
        }
        return true;
    }
    //===/Methods========================================================

    //==Setters and getters==============================================
    public IField getField() {
        return field;
    }

    public void setField(IField field) {
        this.field = field;
    }
    //==/Setters and getters=============================================
}
