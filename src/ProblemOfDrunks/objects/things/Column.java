package ProblemOfDrunks.objects.things;

import ProblemOfDrunks.objects.IGameObject;
import ProblemOfDrunks.objects.IMovingObject;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 18.03.12
 * Time: 18:50
 * To change this template use File | Settings | File Templates.
 */
public class Column extends AFieldObject {
    public Column() {
    }

    @Override
    public void getColliding(IMovingObject object) {
        object.processColliding(this);
    }


    @Override
    public char getSymbol() {
        return 'C';
    }
}
