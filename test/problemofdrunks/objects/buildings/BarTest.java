package problemofdrunks.objects.buildings;

import org.junit.Test;
import org.junit.Before;
import problemofdrunks.field.ICell;
import problemofdrunks.field.IField;
import problemofdrunks.field.CoordinateException;
import problemofdrunks.game.IGame;
import problemofdrunks.objects.MakeActionException;
import problemofdrunks.objects.moving.Drunk;

import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: griver
 * Date: 02.06.12
 * Time: 21:20
 * To change this template use File | Settings | File Templates.
 */
public class BarTest {
    private Bar bar;
    private IField field;
    private ICell entrance;
    private IGame game;


    @Before
    public void init() throws CoordinateException {
        field = mock(IField.class);
        entrance = mock(ICell.class);
        game = mock(IGame.class);

        bar = new Bar(field,entrance, game);

    }

    @Test
    public void delayTest() throws MakeActionException, CoordinateException {
        doReturn(true).when(entrance).isEmpty();
        for(int i =1; i <= 100; ++i) {
            bar.makeAction();
        }

        verify(game, times(100/bar.getDelay())).registerGameObject(any(Drunk.class));
    }

    @Test
    public  void notEmptyEntranceTest() throws MakeActionException{
        doReturn(false).when(entrance).isEmpty();
        bar.setDelay(1);
        bar.makeAction();
        verify(game, never()).registerGameObject(any(Drunk.class));
    }
}
