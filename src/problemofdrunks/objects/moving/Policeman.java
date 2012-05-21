package problemofdrunks.objects.moving;

import problemofdrunks.field.ICell;
import problemofdrunks.field.IPathAlgorithm;
import problemofdrunks.field.exception.InvalidPathArgumentException;
import problemofdrunks.field.exception.PathFindException;
import problemofdrunks.objects.IFieldObject;
import problemofdrunks.objects.IMovingObject;
import problemofdrunks.objects.buildings.PoliceDistrict;
import problemofdrunks.objects.exception.MakeActionException;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 18.03.12
 * Time: 3:28
 * To change this template use File | Settings | File Templates.
 */
public class Policeman extends AMovingObject {
    //===Fields==========================================================
    private ICell target;
    private IFieldObject drunk;

    private IPathAlgorithm pathFinder;
    private PoliceDistrict district;
    //===/Fields=========================================================

    //===Constructors====================================================
    public Policeman(IPathAlgorithm pathFinder) {
        this.pathFinder = pathFinder;
    }
    //===/Constructors===================================================

    //===Methods=========================================================

    @Override
    public void getColliding(IMovingObject object) {
        object.processColliding(this);
    }

    @Override
    public void processColliding(Drunk object) {

        if(object.getState() == DrunkStates.LYING) {
            drunk = object;
            drunk.setCell(null);
            target = district.getEntrance();
        }
    }

    @Override
    public void makeAction() throws MakeActionException {
        try {
            if(target == this.getCell())
                if(target == district.getEntrance()) {
                    district.admitPoliceman(this);
                    return;
                }

            boolean  find = pathFinder.findPath(this.getCell(),this.target);

            if(find) {
                ICell next = pathFinder.getNext(this.getCell(),this.target);
                if(next.isEmpty())
                    this.setCell(next);
                else
                    next.getFieldObject().getColliding(this);
            }
        } catch (PathFindException e) {
            throw new MakeActionException("at Policeman.makeAction()",e);
        }
    }
    //===/Methods========================================================

    //==Setters and getters==============================================
    public void setPathFinder(IPathAlgorithm pathFinder) {
        this.pathFinder = pathFinder;
    }

    public ICell getTarget() {
        return target;
    }

    public void setTarget(ICell target) {
        this.target = target;
    }

    public PoliceDistrict getDistrict() {
        return district;
    }

    public void setDistrict(PoliceDistrict district) {
        this.district = district;
    }

    public void setDrunk(IFieldObject drunk) {
        this.drunk = drunk;
    }

    public IFieldObject getDrunk() {
        return drunk;
    }

    @Override
    public char getSymbol() {
        return 'P';
    }
    //==/Setters and getters=============================================
}
