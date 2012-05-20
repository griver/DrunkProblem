package problemofdrunks.field.impl;


import problemofdrunks.field.ICell;
import problemofdrunks.field.IField;
import problemofdrunks.field.exception.InvalidCoordinateException;
import problemofdrunks.field.exception.NotEmptyCellException;
import problemofdrunks.objects.IFieldObject;


/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 16.05.12
 * Time: 21:13
 * To change this template use File | Settings | File Templates.
 */
public abstract class FlatField implements IField {
    private ICell[][] cells;

    public FlatField(int height, int width) {
        cells = new Cell[height][width];

        for(int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    @Override
    public void addObject(IFieldObject object, int... coordinates) throws InvalidCoordinateException, NotEmptyCellException {
        if(checkCoordinate(coordinates) == false) {
            throw new InvalidCoordinateException();
        }

        int x = coordinates[0];
        int y = coordinates[1];

        if(cells[x][y].isEmpty() == false)
            throw new NotEmptyCellException();

        object.setField(this);
        cells[x][y].setFieldObject(object);
        object.setCell(cells[x][y]);
    }

    @Override
    public ICell getCell(int... coordinates)  throws InvalidCoordinateException {
        if(this.checkCoordinate(coordinates)) {
            return cells[coordinates[0]][coordinates[1]];
        } else {
            throw new InvalidCoordinateException();
        }
    }

    @Override
    public void setCell(ICell cell, int... coordinates) throws InvalidCoordinateException {
        if(checkCoordinate(coordinates)) {
            cells[coordinates[0]][coordinates[1]] = cell;
        } else {
            throw new InvalidCoordinateException();
        }
    }

    public int getHeight() {
        return cells.length;
    }

    public int getWight() {
        if(cells.length > 0) {
            return cells[0].length;
        } else {
            return 0;
        }
    }

    public int getNumberOfCells(){
        return getHeight()*getWight();
    }

    public int[] getSizes() {
        return new int[]{getHeight(), getWight() };
    }

    public boolean checkCoordinate(int... coordinates) {
        if(coordinates.length != 2) {
            return false;
        }

        int x = coordinates[0];
        int y = coordinates[1];

        if(0 <= x && x< cells.length) {
            if(0 <= y && y < cells[0].length) {
                return true;
            }
        }
        return false;
    }
}
