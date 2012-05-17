package ProblemOfDrunks.objects.buildings;

import ProblemOfDrunks.field.exception.CoordinateException;
import ProblemOfDrunks.field.exception.InvalidCoordinateException;
import ProblemOfDrunks.game.IGame;
import ProblemOfDrunks.objects.exception.MakeActionException;
import ProblemOfDrunks.field.exception.NotEmptyCellException;
import ProblemOfDrunks.field.ICell;
import ProblemOfDrunks.field.IField;
import ProblemOfDrunks.objects.IGameObject;
import ProblemOfDrunks.objects.moving.Drunk;
import ProblemOfDrunks.objects.things.Bottle;

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
            System.err.println("Error in Bar.makeAction()");
            throw new MakeActionException();
        }
    }

    private void releaseDrunk() throws CoordinateException {
        Drunk newDrunk = new Drunk();

        newDrunk.setBottle(new Bottle());
        try {
            field.addObject(newDrunk, entrance.getCoordinates());
        } catch(InvalidCoordinateException e) {
            System.err.println("Invalid Entrance coordinate in Bar");
            throw e;
        } catch (NotEmptyCellException e) {
            System.err.println("Bar tried to add Drunk in occupied Cell");
            throw e;
        }
        game.registerActiveObject(newDrunk);
    }
    //===/Methods========================================================
}
