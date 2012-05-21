package problemofdrunks.objects.moving;

import org.junit.Test;
import org.junit.Before;
import problemofdrunks.field.IField;
import problemofdrunks.field.exception.CoordinateException;
import problemofdrunks.field.impl.SquareField;
import problemofdrunks.objects.exception.MakeActionException;
import problemofdrunks.objects.things.Bottle;
import problemofdrunks.objects.things.Column;

import static junit.framework.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 20.05.12
 * Time: 18:44
 * To change this template use File | Settings | File Templates.
 */
public class TestDrunk {
    IField field;
    Drunk drunk;

    @Before
    public void init() throws CoordinateException {
        field = new SquareField(2, 1);
        drunk = new Drunk();
        field.addObject(drunk, 1, 0);
    }

    @Test
    public void testWhenDrunkFalls() throws MakeActionException, CoordinateException{
        Bottle bottle = new Bottle();
        field.addObject(bottle, 0, 0);
        drunk.makeAction();
        assertEquals(drunk,field.getCell(0, 0).getFieldObject());
        assertEquals(DrunkStates.LYING, drunk.getState());
    }

    @Test
    public void testWhenDrunkFullsAsleep() throws MakeActionException, CoordinateException{
        Column column = new Column();
        field.addObject(column, 0, 0);
        drunk.makeAction();
        assertEquals(field.getCell(1, 0),drunk.getCell());
        assertEquals(drunk.getState(), DrunkStates.SLEEP);
    }

}
