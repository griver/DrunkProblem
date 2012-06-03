package problemofdrunks.game.impl;


import org.junit.Test;
import org.junit.Before;
import problemofdrunks.field.IField;
import problemofdrunks.game.IGame;
import problemofdrunks.game.GameStepException;
import problemofdrunks.objects.IGameObject;
import problemofdrunks.objects.MakeActionException;


import static org.mockito.Mockito.*;
import static junit.framework.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: griver
 * Date: 03.06.12
 * Time: 16:46
 * To change this template use File | Settings | File Templates.
 */
public class DrunkGameTest {
    private IGame game;
    private IField field;
    private IGameObject first;
    private IGameObject second;
    private IGameObject third;

    @Before
    public void init() throws GameStepException {
        field = mock(IField.class);
        game = new DrunkGame();
        game.setField(field);

        first = mock(IGameObject.class);
        second = mock(IGameObject.class);
        third = mock(IGameObject.class);

        game.registerGameObject(first);
        game.nextStep();
    }

    @Test
    public void whenNoActiveObjects() throws GameStepException {
        game.removeGameObject(first);
        assertEquals(false, game.nextStep());
    }

    public void nextStepTest() throws GameStepException, MakeActionException {
        game.removeGameObject(first);
        game.registerGameObject(second);
        game.registerGameObject(third);

        assertEquals(true, game.nextStep());

        verify(first,never()).makeAction();
        verify(second).makeAction();
        verify(third).makeAction();
    }


}
