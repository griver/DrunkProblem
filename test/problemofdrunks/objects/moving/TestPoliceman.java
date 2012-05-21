package problemofdrunks.objects.moving;

import org.junit.Test;
import org.junit.Before;

import problemofdrunks.field.ICell;
import problemofdrunks.field.IField;
import problemofdrunks.field.IPathAlgorithm;
import problemofdrunks.field.exception.CoordinateException;
import problemofdrunks.field.exception.PathFindException;
import problemofdrunks.field.impl.SquareField;
import problemofdrunks.objects.buildings.PoliceDistrict;
import problemofdrunks.objects.exception.MakeActionException;

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

    @Before
    public void init() throws CoordinateException {
        pathAlgorithm = mock(IPathAlgorithm.class);
        field = new SquareField(15,15);
        policeman = new Policeman(pathAlgorithm);
        field.addObject(policeman, 0, 0);
    }


    @Test
    public void canTakeDrunkTest() throws CoordinateException, MakeActionException, PathFindException {
        Drunk drunk = new Drunk();
        drunk.setState(DrunkStates.LYING);
        field.addObject(drunk, 1, 3);
        policeman.setTarget(field.getCell(1,3));

        PoliceDistrict district = mock(PoliceDistrict.class);
        policeman.setDistrict(district);
        doReturn(field.getCell(0, 0)).when(district).getEntrance();


        when(pathAlgorithm.findPath(any(ICell.class), any(ICell.class))).thenReturn(true);
        when(pathAlgorithm.getNext(any(ICell.class), any(ICell.class)))
                .thenReturn(field.getCell(0,1))
                .thenReturn(field.getCell(1,1))
                .thenReturn(field.getCell(1,2))
                .thenReturn(field.getCell(1,3));

        for(int i = 0; i < 4; ++i) {
            policeman.makeAction();
        }

        assertEquals(drunk, policeman.getDrunk());
        assertTrue(field.getCell(1,3).isEmpty());
        assertEquals(district.getEntrance(), policeman.getTarget());
        assertEquals(field.getCell(1,2), policeman.getCell());
    }

}
