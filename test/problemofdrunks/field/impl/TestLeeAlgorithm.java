package problemofdrunks.field.impl;

import static org.mockito.Mockito.*;
import static junit.framework.Assert.*;

import org.junit.Test;
import org.junit.Before;
import problemofdrunks.field.ICell;
import problemofdrunks.field.exception.CoordinateException;
import problemofdrunks.field.exception.InvalidPathArgumentException;
import problemofdrunks.field.exception.PathFindException;
import problemofdrunks.objects.IFieldObject;

import java.util.List;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 21.05.12
 * Time: 21:14
 * To change this template use File | Settings | File Templates.
 */
public class TestLeeAlgorithm {
    private SquareField field;
    private LeeAlgorithm leeAlgorithm;

    private ICell start;
    private ICell end;

    private int size;
    private Random random;

    @Before
    public void init() {
        size = 5;
        field = new SquareField(size, size);
        leeAlgorithm = new LeeAlgorithm(field);
        random = new Random();
        start = null;
        end = null;
    }
    @Test
    public void findClearPathTest() throws CoordinateException, PathFindException {
        field.addObject(mock(IFieldObject.class), 1, 1);
        field.addObject(mock(IFieldObject.class), 1, 3);
        field.addObject(mock(IFieldObject.class), 3, 1);
        field.addObject(mock(IFieldObject.class), 3, 3);
        
        for(int i = 0; i < 13; ++i) {
            int x = random.nextInt(size);
            int y = random.nextInt(size);
        
            start = field.getCell(0,0);
            end = field.getCell(x,y);
            
            assertEquals(true, leeAlgorithm.findPath(start,end));
            checkPath(leeAlgorithm.getPath(start, end), start, end);
        }
    }

    @Test
    public void findNullPathTest() throws CoordinateException, PathFindException{
        start = field.getCell(3,3);
        end = start;
        assertEquals(true,leeAlgorithm.findPath(start, end));
        List<ICell> path = leeAlgorithm.getPath(start, end);
        assertEquals(1, path.size());
        assertEquals(end, path.get(0));
    }

    @Test
    public void whenThereIsNoPath() throws CoordinateException, PathFindException{
        start = field.getCell(0,0);
        end = field.getCell(4,4);

        field.addObject(mock(IFieldObject.class), 3, 4);
        field.addObject(mock(IFieldObject.class), 3, 3);
        field.addObject(mock(IFieldObject.class), 4, 3);

        assertEquals(false,leeAlgorithm.findPath(start, end));
        assertEquals(0, leeAlgorithm.getPath(start,end).size());
    }

    @Test(expected = InvalidPathArgumentException.class)
    public void throwsExceptionTest() throws  CoordinateException, PathFindException {
        leeAlgorithm.findPath(new Cell(3, 3), field.getCell(0,0));
    }

    private void checkPath(List<ICell> path, ICell start, ICell end) throws CoordinateException{
        assertEquals(start, path.get(0));
        assertEquals(end, path.get(path.size()-1));

        ICell previous = null;

        for(ICell cell : path) {

            if(cell != end)
                assertEquals(true,cell.isEmpty());

            if(previous != null)
                assertEquals(1,field.distance(previous, cell));

            previous = cell;
        }
    }
}
