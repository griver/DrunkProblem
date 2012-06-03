package problemofdrunks.objects.moving;

import org.junit.Test;
import org.junit.Before;
import problemofdrunks.field.ICell;
import problemofdrunks.field.IField;
import problemofdrunks.field.IPathAlgorithm;
import problemofdrunks.field.CoordinateException;
import problemofdrunks.field.PathFindException;
import problemofdrunks.field.impl.SquareField;
import problemofdrunks.objects.buildings.BottleToMoneyHouse;
import problemofdrunks.objects.MakeActionException;
import problemofdrunks.objects.things.Bottle;


import static org.mockito.Mockito.*;
import static junit.framework.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: griver
 * Date: 02.06.12
 * Time: 19:43
 * To change this template use File | Settings | File Templates.
 */
public class TestBeggar {
    Beggar beggar;
    IPathAlgorithm pathAlgorithm;
    BottleToMoneyHouse bottleToMoneyHouse;
    IField field;

    @Before
    public void init() throws CoordinateException{

        pathAlgorithm = mock(IPathAlgorithm.class);
        bottleToMoneyHouse = mock(BottleToMoneyHouse.class);
        field = new SquareField(3,3);
        beggar = spy(new Beggar(2, pathAlgorithm));

        field.addObject(beggar, 0, 0);
        doReturn(field.getCell(0,0)).when(bottleToMoneyHouse).getEntrance();
        beggar.setHouse(bottleToMoneyHouse);
    }

    @Test
    public void whenBottleCloseTest() throws CoordinateException, MakeActionException{
        Bottle bottle = mock(Bottle.class);
        field.addObject(bottle, 1, 1);

        doReturn(field.getCell(1,1)).when(bottle).getCell();
        beggar.makeAction();

        verify(bottle).getCell();
        assertEquals(bottle.getCell(), field.getCell(1, 1));
    }


    @Test
    public void whenBottleFarTest() throws CoordinateException, MakeActionException {
        Bottle bottle = mock(Bottle.class);
        field.addObject(bottle, 2, 2);

        beggar.makeAction();

        verify(beggar, never()).setTarget(any(ICell.class));
    }

    @Test()
    public void getBottleTest() throws CoordinateException, MakeActionException, PathFindException {
        Bottle bottle = mock(Bottle.class);
        field.addObject(bottle, 0, 1);
        doReturn(field.getCell(0,1)).when(bottle).getCell();
        beggar.setTarget(bottle.getCell());
        doReturn(true).when(pathAlgorithm).findPath(any(ICell.class), any(ICell.class));
        doReturn(bottle.getCell()).when(pathAlgorithm).getNext(any(ICell.class), any(ICell.class));

        beggar.makeAction();

        verify(bottle).setCell(null);
        verify(bottleToMoneyHouse, times(2)).getEntrance();
        assertEquals(beggar.getBottle(), bottle);
    }
}
