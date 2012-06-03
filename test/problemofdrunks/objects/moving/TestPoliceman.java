package problemofdrunks.objects.moving;

import org.junit.Test;
import org.junit.Before;

import problemofdrunks.field.ICell;
import problemofdrunks.field.IField;
import problemofdrunks.field.IPathAlgorithm;
import problemofdrunks.field.CoordinateException;
import problemofdrunks.field.PathFindException;
import problemofdrunks.field.impl.SquareField;
import problemofdrunks.objects.buildings.PoliceDistrict;
import problemofdrunks.objects.MakeActionException;

import static org.mockito.Mockito.*;
import static junit.framework.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 20.05.12
 * Time: 18:30
 * To change this template use File | Settings | File Templates.
 */
public class TestPoliceman {
    IField field;
    IPathAlgorithm pathAlgorithm;
    Policeman policeman;
    PoliceDistrict district;

    @Before
    public void init() throws CoordinateException {
        pathAlgorithm = mock(IPathAlgorithm.class);
        district = mock(PoliceDistrict.class);
        field = new SquareField(2,2);
        policeman = new Policeman(pathAlgorithm);

        field.addObject(policeman, 0, 0);
        doReturn(field.getCell(0, 0)).when(district).getEntrance();
        policeman.setDistrict(district);

    }


    @Test
    public void canTakeDrunkTest() throws CoordinateException, MakeActionException, PathFindException {
        Drunk drunk = spy(new Drunk());
        field.addObject(drunk, 0,1);
        drunk.setState(DrunkStates.LYING);

        policeman.setTarget(field.getCell(0,1));


        when(pathAlgorithm.findPath(any(ICell.class), any(ICell.class))).thenReturn(true);
        when(pathAlgorithm.getNext(any(ICell.class), any(ICell.class))).thenReturn(field.getCell(0,1));

        policeman.makeAction();

        assertEquals(drunk, policeman.getDrunk());
        assertTrue(field.getCell(0,1).isEmpty());
        assertEquals(district.getEntrance(), policeman.getTarget());
        assertEquals(field.getCell(0,0), policeman.getCell());

        verify(drunk).getColliding(policeman);
        verify(drunk).setCell(null);
    }

    @Test
    public void whenDrunkIsNotLyingTest() throws CoordinateException, MakeActionException, PathFindException {
        Drunk drunk= mock(Drunk.class);
        field.addObject(drunk, 0,1);

        doReturn(DrunkStates.AWAKE).when(drunk).getState();
        policeman.processColliding(drunk);

        verify(drunk).getState();
        verify(drunk, never()).setCell(null);

        assertEquals(policeman.getDrunk(), null);
        assertEquals(field.getCell(0, 1).getFieldObject(), drunk);
    }
    @Test
    public void whenGoToDistrict() throws  MakeActionException {
        policeman.setTarget(district.getEntrance());
        policeman.makeAction();

        verify(district).admitPoliceman(policeman);
    }
}
