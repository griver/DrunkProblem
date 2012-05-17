package ProblemOfDrunks.objects;

import ProblemOfDrunks.field.ICell;
import ProblemOfDrunks.field.IField;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 18.03.12
 * Time: 2:57
 * To change this template use File | Settings | File Templates.
 */
public interface IFieldObject{
    void getColliding(IMovingObject object);

    void setField(IField field);

    ICell getCell();

    void setCell(ICell cell);

    public char getSymbol();
}
