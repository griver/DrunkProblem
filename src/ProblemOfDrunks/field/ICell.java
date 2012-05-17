package ProblemOfDrunks.field;

import ProblemOfDrunks.objects.IFieldObject;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 17.03.12
 * Time: 19:33
 * To change this template use File | Settings | File Templates.
 */
public interface ICell {
    boolean isEmpty();
    
    IFieldObject getFieldObject();
    
    void setFieldObject(IFieldObject object);
    
    void setCoordinates(int... values);
    
    int[] getCoordinates();

    char getSymbol();
}
