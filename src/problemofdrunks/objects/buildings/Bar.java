package problemofdrunks.objects.buildings;

import problemofdrunks.game.IGame;
import problemofdrunks.objects.MakeActionException;
import problemofdrunks.field.ICell;
import problemofdrunks.field.IField;
import problemofdrunks.objects.IGameObject;
import problemofdrunks.objects.moving.Drunk;
import problemofdrunks.objects.things.Bottle;
import problemofdrunks.field.CoordinateException;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 18.03.12
 * Time: 3:43
 * To change this template use File | Settings | File Templates.
 */

public class Bar implements IGameObject {
    //===Fields==========================================================
    private IField field;
    private ICell entrance;
    private int counter;
    private IGame game;
    private int delay;
    //===/Fields=========================================================

    //===Constructors====================================================
    public Bar(IField field, ICell entrance, IGame game) {
        this.field = field;
        this.entrance = entrance;
        this.game = game;
        this.counter = 0;
        delay = 20;
    }

    public Bar(IField field, ICell entrance, IGame game, int delay) {
        this.field = field;
        this.entrance = entrance;
        this.game = game;
        this.counter = 0;
        this.delay = delay;
    }
    //===/Constructors===================================================

    //===Methods=========================================================
    @Override
    public void makeAction() throws MakeActionException {

        ++counter;
        if(counter % delay == 0) {
            if(entrance.isEmpty())
                releaseDrunk();
        }
    }

    private void releaseDrunk() throws MakeActionException {
        Drunk newDrunk = new Drunk();
        newDrunk.setBottle(new Bottle());

        try {
            field.addObject(newDrunk, entrance.getCoordinates());
        } catch(CoordinateException e) {
            throw new MakeActionException("Error when try to add drunk", e);
        }
        game.registerGameObject(newDrunk);
    }
    //===/Methods========================================================

    //===Setters and getters=============================================

    //===/Setters and getters============================================

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
