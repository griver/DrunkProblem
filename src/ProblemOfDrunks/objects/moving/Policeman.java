package ProblemOfDrunks.objects.moving;

import ProblemOfDrunks.field.ICell;
import ProblemOfDrunks.field.IPathAlgorithm;
import ProblemOfDrunks.objects.IFieldObject;
import ProblemOfDrunks.objects.IMovingObject;
import ProblemOfDrunks.objects.buildings.PoliceDistrict;

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
            ICell drunkCell = object.getCell();
            drunkCell.setFieldObject(null);

            object.setCell(null);
            drunk = object;
        }
    }

    @Override
    public void makeAction() {
        boolean  find = pathFinder.findPath(this.getCell(),this.target);
        if(find) {
            ICell next = pathFinder.getNext(this.getCell(),this.target);
            if(next == target) {
                if(next == district.getEntrance()) {  // вернулся к входу в участок
                    district.admitPoliceman(this);
                } else { // дошел до пьяницы
                    drunk = target.getFieldObject();
                    drunk.setCell(null);
                    target = district.getEntrance();
                }
            } else if(next.isEmpty()) {
                this.setCell(next);
            }
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
