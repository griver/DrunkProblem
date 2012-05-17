package ProblemOfDrunks.objects.things;

import ProblemOfDrunks.objects.IGameObject;
import ProblemOfDrunks.objects.IMovingObject;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 18.03.12
 * Time: 3:11
 * To change this template use File | Settings | File Templates.
 */
public class Bottle extends AFieldObject {
    public Bottle() {
    }

    @Override
    public void getColliding(IMovingObject object) {
        object.processColliding(this);
    }

    @Override
    public char getSymbol() {
        return 'B';
    }

}
