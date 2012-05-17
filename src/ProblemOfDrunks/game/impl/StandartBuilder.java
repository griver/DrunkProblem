package ProblemOfDrunks.game.impl;

import ProblemOfDrunks.field.impl.LeeAlgorithm;
import ProblemOfDrunks.game.exeption.GameBuilderException;
import ProblemOfDrunks.field.exception.InvalidCoordinateException;
import ProblemOfDrunks.field.exception.NotEmptyCellException;
import ProblemOfDrunks.field.IField;
import ProblemOfDrunks.game.IGame;
import ProblemOfDrunks.game.IGameBuilder;
import ProblemOfDrunks.objects.buildings.Bar;
import ProblemOfDrunks.objects.buildings.BottleToMoneyHouse;
import ProblemOfDrunks.objects.buildings.PoliceDistrict;
import ProblemOfDrunks.objects.moving.Beggar;
import ProblemOfDrunks.objects.moving.Policeman;
import ProblemOfDrunks.objects.things.Bottle;
import ProblemOfDrunks.objects.things.Column;
import ProblemOfDrunks.objects.things.Lantern;

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
            System.err.println("Invalid objects coordinates");
            throw new GameBuilderException();
        } catch (NotEmptyCellException e) {
            System.err.println("Try to add object on occupied cell");
            throw new GameBuilderException();
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
