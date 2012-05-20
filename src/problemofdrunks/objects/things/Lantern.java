package problemofdrunks.objects.things;

import problemofdrunks.field.exception.InvalidCoordinateException;
import problemofdrunks.field.ICell;
import problemofdrunks.objects.IMovingObject;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 18.03.12
 * Time: 3:24
 * To change this template use File | Settings | File Templates.
 */
public class Lantern extends AFieldObject {
    private int brightness;

    public Lantern( int brightness ) {
        this.brightness = brightness;
    }

    public boolean isLighted(ICell fieldCell)throws InvalidCoordinateException {
        try {

            if(getField().distance(this.getCell(), fieldCell) <= brightness)
                return true;

        } catch (InvalidCoordinateException e) {
            e.printStackTrace(System.err);
            throw e;
        }

        return false;
    }

    @Override
    public void getColliding(IMovingObject object) {
        object.processColliding(this);
    }


    @Override
    public char getSymbol() {
        return '|';
    }
}
