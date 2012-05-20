package problemofdrunks.field;

import problemofdrunks.field.exception.InvalidCoordinateException;
import problemofdrunks.field.exception.NotEmptyCellException;
import problemofdrunks.objects.IFieldObject;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 14.03.12
 * Time: 13:25
 * To change this template use File | Settings | File Templates.
 */
public interface IField {
    List<ICell> getCellNeighbors(ICell cell) throws InvalidCoordinateException;
    void addObject(IFieldObject object, int... coordinates) throws InvalidCoordinateException, NotEmptyCellException;
    ICell getCell(int... coordinates) throws InvalidCoordinateException;
    void setCell(ICell cell, int... coordinates) throws InvalidCoordinateException;
    int getNumberOfCells();
    int[] getSizes();
    int distance(ICell a, ICell b) throws InvalidCoordinateException;
}
