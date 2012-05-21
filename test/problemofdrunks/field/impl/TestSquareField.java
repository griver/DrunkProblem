package problemofdrunks.field.impl;

import org.junit.Test;
import org.junit.Before;
import junit.framework.Assert;

import problemofdrunks.field.ICell;
import problemofdrunks.field.IField;
import problemofdrunks.field.exception.InvalidCoordinateException;
import problemofdrunks.field.exception.NotEmptyCellException;

import problemofdrunks.objects.things.Column;
import problemofdrunks.objects.things.Lantern;

import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 19.05.12
 * Time: 16:35
 * To change this template use File | Settings | File Templates.
 */
public class TestSquareField {
    IField field;

    @Before
    public void initField() {
        field =  new SquareField(10, 10);
    }

    @Test
    public void distanceTest() throws InvalidCoordinateException {
        ICell cellA = field.getCell(0, 0);
        ICell cellB = field.getCell(9, 9);
        Assert.assertEquals(18, field.distance(cellA, cellB));
        Assert.assertEquals(0, field.distance(cellA, cellA));
    }

    @Test
    public void getNeighboursTest() throws InvalidCoordinateException {
        ICell centerCell = field.getCell(5,5);
        ICell borderCell = field.getCell(0,5);
        ICell cornerCell = field.getCell(0,0);

        Assert.assertEquals(4, field.getCellNeighbors(centerCell).size());
        this.checkNeighboursDistance(centerCell, field.getCellNeighbors(centerCell));

        Assert.assertEquals(3, field.getCellNeighbors(borderCell).size());
        this.checkNeighboursDistance(borderCell, field.getCellNeighbors(borderCell));

        Assert.assertEquals(2, field.getCellNeighbors(cornerCell).size());
        this.checkNeighboursDistance(cornerCell, field.getCellNeighbors(cornerCell));
    }

    @Test(expected = InvalidCoordinateException.class)
    public void invalidCoordinateExceptionsTest() throws InvalidCoordinateException {
        field.getCell(0,10);
    }

    @Test(expected = NotEmptyCellException.class)
    public void notEmptyCellExceptionTest() throws NotEmptyCellException, InvalidCoordinateException {
        field.addObject(new Column(), 5, 5);
        field.addObject(new Lantern(3), 5, 5);
    }

    public void checkNeighboursDistance(ICell cell, List<ICell> neighbours) throws InvalidCoordinateException{
        for(ICell nb : neighbours) {
            Assert.assertEquals(1,field.distance(cell, nb));
        }
    }
}
