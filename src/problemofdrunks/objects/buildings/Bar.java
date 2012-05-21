package problemofdrunks.objects.buildings;

import problemofdrunks.field.exception.InvalidCoordinateException;
import problemofdrunks.game.IGame;
import problemofdrunks.objects.exception.MakeActionException;
import problemofdrunks.field.exception.NotEmptyCellException;
import problemofdrunks.field.ICell;
import problemofdrunks.field.IField;
import problemofdrunks.objects.IGameObject;
import problemofdrunks.objects.moving.Drunk;
import problemofdrunks.objects.things.Bottle;
import problemofdrunks.field.exception.CoordinateException;

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
    //===/Fields=========================================================

    //===Constructors====================================================
    public Bar(IField field, ICell entrance, IGame game) {
        this.field = field;
        this.entrance = entrance;
        this.game = game;
        counter = 0;
    }
    //===/Constructors===================================================

    //===Methods=========================================================
    @Override
    public void makeAction() throws MakeActionException {
        try{
            ++counter;
            if(counter % 20 == 0) {
                if(entrance.isEmpty())
                    releaseDrunk();
            }
        }catch (CoordinateException e) {
            throw new MakeActionException("Error in Bar.makeAction()", e);
        }
    }

    private void releaseDrunk() throws CoordinateException {
        Drunk newDrunk = new Drunk();

        newDrunk.setBottle(new Bottle());
        try {
            field.addObject(newDrunk, entrance.getCoordinates());
        } catch(InvalidCoordinateException e) {
            throw new InvalidCoordinateException("Invalid Entrance coordinate in Bar", e);
        } catch (NotEmptyCellException e) {
            System.err.println();
            throw new NotEmptyCellException("Bar tried to add Drunk in occupied Cell", e);
        }
        game.registerActiveObject(newDrunk);
    }
    //===/Methods========================================================
}
