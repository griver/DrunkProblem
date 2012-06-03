package problemofdrunks.objects.buildings;


import org.junit.Test;
import org.junit.Before;
import problemofdrunks.field.ICell;
import problemofdrunks.field.IField;
import problemofdrunks.field.CoordinateException;
import problemofdrunks.field.impl.SquareField;
import problemofdrunks.game.IGame;
import problemofdrunks.objects.MakeActionException;
import problemofdrunks.objects.moving.Drunk;
import problemofdrunks.objects.moving.DrunkStates;
import problemofdrunks.objects.moving.Policeman;
import problemofdrunks.objects.things.Lantern;

import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: griver
 * Date: 03.06.12
 * Time: 15:23
 * To change this template use File | Settings | File Templates.
 */
public class PoliceDistrictTest {
    private PoliceDistrict district;
    private IField field;
    private IGame game;
    private ICell entrance;
    private ICell lighted;
    private Policeman policeman;
    private Lantern lantern;


    @Before
    public void init() throws CoordinateException {
        policeman = mock(Policeman.class);
        game = mock(IGame.class);

        field = spy(new SquareField(4,4));
        entrance = spy(field.getCell(0,0));
        initLantern();

        district =new PoliceDistrict(field, entrance, game);
        district.addPoliceman(policeman);
        district.setLantern(lantern);
    }

    public void initLantern() throws  CoordinateException {
        lantern = mock(Lantern.class);
        doReturn(field.getCell(2,2)).when(lantern).getCell();
        doReturn(false).when(lantern).isLighted(any(ICell.class));

        lighted = field.getCellNeighbors(lantern.getCell()).get(0);
        doReturn(true).when(lantern).isLighted(lighted);
    }

    @Test
    public void findLyingDrunkTest() throws MakeActionException, CoordinateException {

        Drunk drunk = mock(Drunk.class);
        doReturn(DrunkStates.LYING).when(drunk).getState();
        doReturn(lighted).when(drunk).getCell();

        field.addObject(drunk, lighted.getCoordinates());

        district.makeAction();

        verify(game).registerGameObject(policeman);
        verify(field).addObject(policeman, entrance.getCoordinates());
        verify(policeman).setTarget(drunk.getCell());
    }

    @Test
    public void findAwakeDrunkTest() throws CoordinateException, MakeActionException {
        Drunk drunk = mock(Drunk.class);
        doReturn(DrunkStates.AWAKE).when(drunk).getState();
        doReturn(lighted).when(drunk).getCell();

        field.addObject(drunk, lighted.getCoordinates());

        district.makeAction();

        verify(game, never()).registerGameObject(policeman);
        verify(field, never()).addObject(policeman, entrance.getCoordinates());
        verify(policeman, never()).setTarget(drunk.getCell());
    }

    public void NoLightedDrunkMan() throws CoordinateException, MakeActionException {
        Drunk drunk = mock(Drunk.class);
        doReturn(DrunkStates.LYING).when(drunk).getState();
        doReturn(field.getCell(1,1)).when(drunk).getCell();

        field.addObject(drunk, 1, 1);

        district.makeAction();

        verify(game, never()).registerGameObject(policeman);
        verify(field, never()).addObject(policeman, entrance.getCoordinates());
        verify(policeman, never()).setTarget(drunk.getCell());
    }

}
