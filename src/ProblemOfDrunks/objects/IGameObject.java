package ProblemOfDrunks.objects;

import ProblemOfDrunks.objects.exception.MakeActionException;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 07.03.12
 * Time: 21:42
 * To change this template use File | Settings | File Templates.
 */
public interface IGameObject {
    void makeAction() throws MakeActionException;
}
