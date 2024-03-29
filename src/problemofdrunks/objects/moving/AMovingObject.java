package problemofdrunks.objects.moving;

import problemofdrunks.field.ICell;
import problemofdrunks.field.IField;
import problemofdrunks.objects.IFieldObject;
import problemofdrunks.objects.IMovingObject;
import problemofdrunks.objects.things.Bottle;
import problemofdrunks.objects.things.Lantern;
import problemofdrunks.objects.things.Column;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 07.04.12
 * Time: 15:29
 * To change this template use File | Settings | File Templates.
 */
public abstract class AMovingObject implements IMovingObject {
    //===Fields==========================================================
    private ICell cell;
    private IField field;
    //===/Fields=========================================================

    //===Methods=========================================================
    @Override
    public void processColliding(Drunk object) {
        return;
    }

    @Override
    public void processColliding(Policeman object) {
        return;
    }

    @Override
    public void processColliding(Lantern object) {
        return;
    }

    @Override
    public void processColliding(Bottle object) {
        return;
    }

    @Override
    public void processColliding(Column object) {
        return;
    }

    //@Override
    public void processColliding(IFieldObject object) {
        return;
    }

    @Override
    public void processColliding(Beggar object) {
        return;
    }

    //===/Methods========================================================

    //==Setters and getters==============================================
    public IField getField() {
        return field;
    }

    public void setField(IField field) {
        this.field = field;
    }

    @Override
    public ICell getCell() {
        return cell;
    }

    @Override
    public void setCell(ICell cell) {
        if(this.cell != null) {
            this.cell.setFieldObject(null);
        }

        this.cell = cell;

        if(this.cell != null) {
            this.cell.setFieldObject(this);
        }
    }
    //==/Setters and getters=============================================
}
