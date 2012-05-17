package ProblemOfDrunks.field.impl;

import ProblemOfDrunks.field.ICell;
import ProblemOfDrunks.objects.IFieldObject;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 18.03.12
 * Time: 2:12
 * To change this template use File | Settings | File Templates.
 */
public class Cell implements ICell {
    //===Fields==========================================================
    IFieldObject object;
    int[] coordinates = null;
    //===/Fields=========================================================

    //===Constructors====================================================
    Cell(int... values) {
        this.setCoordinates(values);
    }
    //===/Constructors===================================================

    //===Methods=========================================================
    @Override
    public boolean isEmpty() {
        return (object == null);
    }
    //===/Methods========================================================

    //==Setters and getters==============================================
    @Override
    public IFieldObject getFieldObject() {
        return this.object;
    }

    @Override
    public void setFieldObject(IFieldObject object) {
        this.object =  object;
    }

    @Override
    public void setCoordinates(int... values) {
        coordinates = values.clone();
    }

    @Override
    public int[] getCoordinates() {
        return this.coordinates;
    }

    public char getSymbol() {
        if(this.isEmpty()) return '';
        else return object.getSymbol();
    }
    //==/Setters and getters=============================================

}
