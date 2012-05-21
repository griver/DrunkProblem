package problemofdrunks.game.impl;

import problemofdrunks.field.exception.InvalidCoordinateException;
import problemofdrunks.field.exception.NotEmptyCellException;
import problemofdrunks.field.impl.LeeAlgorithm;
import problemofdrunks.field.IField;
import problemofdrunks.game.exeption.GameBuilderException;
import problemofdrunks.game.IGame;
import problemofdrunks.game.IGameBuilder;
import problemofdrunks.objects.buildings.Bar;
import problemofdrunks.objects.moving.Beggar;
import problemofdrunks.objects.buildings.BottleToMoneyHouse;
import problemofdrunks.objects.buildings.PoliceDistrict;
import problemofdrunks.objects.moving.Policeman;
import problemofdrunks.objects.things.Bottle;
import problemofdrunks.objects.things.Lantern;
import problemofdrunks.objects.things.Column;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 07.04.12
 * Time: 19:40
 * To change this template use File | Settings | File Templates.
 */

public class StandartBuilder implements IGameBuilder {
    //===Fields=================================================
    private IField field;
    private IGame game;
    //===/Fields================================================
    public StandartBuilder(IField field, IGame game) {
        this.field = field;
        this.game = game;
    }

    //===Methods================================================
    @Override
    public IGame buildGame() throws GameBuilderException {
        try{
            if(game == null || field == null)
                return null;
            Lantern lantern = new Lantern(3);
            Column column = new Column();
            Policeman policeman = new Policeman(new LeeAlgorithm(field));
            Beggar beggar = new Beggar(3, new LeeAlgorithm(field));
            Bottle bottle = new Bottle();
            Bottle bottle1 = new Bottle();
    
    
            Bar bar = new Bar(field, field.getCell(0,9), game);
            PoliceDistrict district = new PoliceDistrict(field, field.getCell(3, 14), game);
            BottleToMoneyHouse beggarHouse = new BottleToMoneyHouse(field, field.getCell(14,4), game);
    
            beggarHouse.setBeggar(beggar);
    
            district.addPoliceman(policeman);
            district.setLantern(lantern);

            field.addObject(lantern,3, 10);
            field.addObject(column, 7, 7);
            field.addObject(bottle, 11, 3);
            field.addObject(bottle1, 10, 7);
    
            game.setField(field);
            game.registerActiveObject(district);
            game.registerActiveObject(bar);
            game.registerActiveObject(beggarHouse);

        } catch (InvalidCoordinateException e) {
            throw new GameBuilderException("Invalid objects coordinates", e);
        } catch (NotEmptyCellException e) {
            throw new GameBuilderException("Try to add object on occupied cell", e);
        }

        return game;
    }
    //===/Methods===============================================

    //===Setters and Getters====================================
    public void setGame(IGame game) {
        this.game = game;
    }

    public void setField(IField field) {
        this.field = field;
    }
    //===/Setters and Getters===================================
}
