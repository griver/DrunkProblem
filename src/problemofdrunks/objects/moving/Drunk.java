package problemofdrunks.objects.moving;

import problemofdrunks.field.exception.InvalidCoordinateException;
import problemofdrunks.field.ICell;
import problemofdrunks.objects.IFieldObject;
import problemofdrunks.objects.exception.MakeActionException;
import problemofdrunks.objects.IMovingObject;
import problemofdrunks.objects.things.Bottle;
import problemofdrunks.objects.things.Column;

import java.util.List;


import java.util.Random;
/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 18.03.12
 * Time: 2:49
 * To change this template use File | Settings | File Templates.
 */
public class Drunk extends AMovingObject {
    //===Fields==========================================================
    private Bottle bottle;
    private DrunkStates state = DrunkStates.AWAKE;
    static public final Random random = new Random();
    //===/Fields=========================================================

    //===Constructors====================================================
    public Drunk() {
    }
    //===/Constructors===================================================

    //===Methods=========================================================
    @Override
    public void processColliding(Drunk object) {
        if (object.getState() == DrunkStates.SLEEP)
            setState(DrunkStates.SLEEP);
    }

    @Override
    public void processColliding(Bottle object) {
        ICell nextCell = object.getCell();
        object.setCell(null);
        this.setCell(nextCell);

        this.setState(DrunkStates.LYING);
    }

    @Override
    public void processColliding(Column object) {
        this.setState(DrunkStates.SLEEP);
    }


    @Override
    public void getColliding(IMovingObject object) {
        object.processColliding(this);
    }

    @Override
    public void makeAction() throws MakeActionException {
        try {
            if(this.state != DrunkStates.AWAKE)
                return;

            List<ICell> neighbors = getField().getCellNeighbors(getCell());
            int n = random.nextInt(neighbors.size());
            ICell nextCell = neighbors.get(n);

            if(nextCell.isEmpty()) {
                ICell oldCell = getCell();
                setCell(null);

                if(bottle != null) {
                    int rand = random.nextInt(30);
                    if(rand == 0)
                        dropBottle(oldCell);
                }

                this.setCell(nextCell);

            } else {
                IFieldObject obj = nextCell.getFieldObject();
                obj.getColliding(this);
            }
        } catch(InvalidCoordinateException e) {
            throw new MakeActionException("Invalid Coordinate in Drunk.makeAction()",e);
        }

    }

    private void dropBottle(ICell cell) {
        bottle.setCell(cell);
        this.bottle = null;
    }
    //===/Methods========================================================

    //==Setters and getters==============================================
    public DrunkStates getState() {
        return state;
    }

    public void setState(DrunkStates state) {
        this.state = state;
    }

    @Override
    public char getSymbol() {
        if(state == DrunkStates.AWAKE)
            return 'A';
        else if(state == DrunkStates.SLEEP)
            return 'S';
        else
            return 'L';
    }

    public void setBottle(Bottle bottle) {
        this.bottle = bottle;
    }
    //==/Setters and getters=============================================
}
