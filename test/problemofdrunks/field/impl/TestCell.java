package problemofdrunks.field.impl;

import org.junit.Test;
import junit.framework.Assert;
import org.junit.*;

import problemofdrunks.objects.things.Column;


/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 19.05.12
 * Time: 14:54
 * To change this template use File | Settings | File Templates.
 */

public class TestCell {

    @Test
    public void setCoordinateTest() {
        Cell cell = new Cell(2, 4);
        int [] coord = {1, 5};
        cell.setCoordinates(coord);
        this.compareArray(coord, cell.getCoordinates());
    }

    @Test
    public void cellConstructorTest() {
        int [] coord = {2,4};
        Cell cell = new Cell(coord);
        compareArray(coord, cell.getCoordinates());
        Assert.assertTrue(cell.isEmpty());
    }

    @Test
    public void setFieldObjectTest() {
        Cell cell = new Cell(2, 5);
        Column column = new Column();
        cell.setFieldObject(column);

        Assert.assertTrue(column == cell.getFieldObject());
        Assert.assertTrue(cell.getSymbol() == column.getSymbol());
    }        
    
    void compareArray(int[] a, int [] b) {
        Assert.assertTrue(a.length == b.length );
        for(int i =0; i< a.length; ++i) {
            Assert.assertTrue(a[i] == b[i]);
        }
    }
}
