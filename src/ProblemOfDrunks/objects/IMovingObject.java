package ProblemOfDrunks.objects;

import ProblemOfDrunks.objects.moving.Beggar;
import ProblemOfDrunks.objects.moving.Drunk;
import ProblemOfDrunks.objects.moving.Policeman;
import ProblemOfDrunks.objects.things.Bottle;
import ProblemOfDrunks.objects.things.Column;
import ProblemOfDrunks.objects.things.Lantern;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 18.03.12
 * Time: 18:45
 * To change this template use File | Settings | File Templates.
 */
public interface IMovingObject extends IFieldObject, IGameObject {
    void processColliding(Drunk object);
    void processColliding(Policeman object);
    void processColliding(Lantern object);
    void processColliding(Bottle object);
    void processColliding(Column object);
    void processColliding(Beggar object);
    void processColliding(IFieldObject object);
}
