package problemofdrunks.objects;

import problemofdrunks.objects.moving.Beggar;
import problemofdrunks.objects.moving.Drunk;
import problemofdrunks.objects.things.Bottle;
import problemofdrunks.objects.things.Lantern;
import problemofdrunks.objects.moving.Policeman;
import problemofdrunks.objects.things.Column;

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
