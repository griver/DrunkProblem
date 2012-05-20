package problemofdrunks.objects.buildings;

import problemofdrunks.field.ICell;
import problemofdrunks.field.IField;
import problemofdrunks.game.IGame;
import problemofdrunks.objects.IGameObject;
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
    //===/Fields================================================

    //===Constructors===========================================
    public BottleToMoneyHouse(IField field, ICell entrance, IGame game) {
        this.field = field;
        this.entrance = entrance;
        this.game = game;
    }
    //===/Constructors==========================================

    //===Methods================================================
    @Override
    public void makeAction() {
        if(beggar != null)
            counter++;
        if(counter  == 40)
            releaseBeggar();
    }


    public void letIn(Beggar beggar) {
        beggar.setBottle(null);
        beggar.setCell(null);
        beggar.setTarget(null);

        game.removeActiveObject(beggar);
        this.beggar = beggar;
    }

    private void releaseBeggar() {
        if(beggar == null)
            return;
        Beggar beggar = this.beggar;
        beggar.setCell(entrance);
        game.registerActiveObject(beggar);

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
    //===/Setters and getters===================================
}
