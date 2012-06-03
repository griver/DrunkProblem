package problemofdrunks.objects.buildings;

import problemofdrunks.field.ICell;
import problemofdrunks.field.IField;
import problemofdrunks.field.CoordinateException;
import problemofdrunks.game.IGame;
import problemofdrunks.objects.IGameObject;
import problemofdrunks.objects.MakeActionException;
import problemofdrunks.objects.moving.Beggar;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 14.04.12
 * Time: 17:27
 * To change this template use File | Settings | File Templates.
 */
public class BottleToMoneyHouse implements IGameObject {
    //===Fields===============================================
    private IField field;
    private ICell entrance;
    private IGame game;
    private Beggar beggar = null;
    private int counter = 0;
    private int delay = 0;
    //===/Fields================================================

    //===Constructors===========================================
    public BottleToMoneyHouse(IField field, ICell entrance, IGame game, int delay) {
        this.field = field;
        this.entrance = entrance;
        this.game = game;
        this.delay = delay;
    }
    //===/Constructors==========================================

    //===Methods================================================
    @Override
    public void makeAction() throws  MakeActionException {
        if(beggar != null)
            counter++;
        if(counter == delay) {
            if(entrance.isEmpty())
                releaseBeggar();
        }
    }


    public void letIn(Beggar beggar) throws MakeActionException {
        beggar.setBottle(null);
        beggar.setCell(null);
        beggar.setTarget(null);

        game.removeGameObject(beggar);
        this.beggar = beggar;
    }

    private void releaseBeggar() throws MakeActionException {
        if(beggar == null)
            return;

        Beggar beggar = this.beggar;

        try{
            field.addObject(beggar, entrance.getCoordinates());
        } catch(CoordinateException e) {
            throw new MakeActionException("Error when try to add beggar", e);
        }

        game.registerGameObject(beggar);
        this.beggar = null;
        counter = 0;
    }
    //===/Methods===============================================

    //===Setters and getters====================================
    public void setBeggar(Beggar beggar) {
        beggar.setField(field);
        beggar.setBottle(null);
        beggar.setHouse(this);
        beggar.setTarget(null);
        this.beggar = beggar;
    }

    public ICell getEntrance() {
        return entrance;
    }

    public void setEntrance(ICell entrance) {
        this.entrance = entrance;
    }

    public IGame getGame() {
        return game;
    }

    public void setGame(IGame game) {
        this.game = game;
    }

    public IField getField() {
        return field;
    }

    public void setField(IField field) {
        this.field = field;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getDelay() {
        return delay;
    }
    //===/Setters and getters===================================
}
