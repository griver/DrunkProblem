package problemofdrunks.field.impl;

import problemofdrunks.field.ICell;
import problemofdrunks.field.exception.InvalidCoordinateException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 17.03.12
 * Time: 19:52
 * To change this template use File | Settings | File Templates.
 */
public class SquareField extends FlatField {

    //===Constructors====================================================
    public SquareField(int height, int width) {
        super(height, width);
    }
    //===/Constructors===================================================

    //===Methods=========================================================
    @Override
    public List<ICell> getCellNeighbors(ICell cell) throws InvalidCoordinateException {
        int[] coordinates = cell.getCoordinates();
        if(super.checkCoordinate(coordinates) == false)
            throw new InvalidCoordinateException();

        ArrayList<ICell> neighbors  = new ArrayList<ICell>();

        int x = coordinates[0];
        int y = coordinates[1];

        if(super.checkCoordinate(x - 1, y)) {
            neighbors.add(super.getCell(x - 1, y));
        }

        if(super.checkCoordinate(x, y - 1)) {
            neighbors.add(super.getCell(x, y - 1));
        }

        if(super.checkCoordinate(x + 1, y)) {
            neighbors.add(super.getCell(x + 1,y));
        }

        if(super.checkCoordinate(x, y + 1)) {
            neighbors.add(super.getCell(x, y + 1));
        }

        return neighbors;
    }

    @Override //бросить исключение полюбасу
    public int distance(ICell a, ICell b) throws InvalidCoordinateException  {
        if(super.checkCoordinate(a.getCoordinates()) == false || super.checkCoordinate(b.getCoordinates()) == false)
            throw  new InvalidCoordinateException();
        int aX = a.getCoordinates()[0];
        int aY = a.getCoordinates()[1];
        int bX = b.getCoordinates()[0];
        int bY = b.getCoordinates()[1];
        return Math.abs(aX-bX) + Math.abs(aY -bY);
    }

    //===/Methods========================================================
}
