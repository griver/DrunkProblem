package problemofdrunks.objects.moving;

import problemofdrunks.field.IPathAlgorithm;
import problemofdrunks.field.exception.CoordinateException;
import problemofdrunks.field.exception.InvalidCoordinateException;
import problemofdrunks.field.exception.PathFindException;
import problemofdrunks.objects.exception.MakeActionException;
import problemofdrunks.field.ICell;
import problemofdrunks.objects.IFieldObject;
import problemofdrunks.objects.IMovingObject;
import problemofdrunks.objects.buildings.BottleToMoneyHouse;
import problemofdrunks.objects.things.Bottle;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 14.04.12
 * Time: 16:27
 * To change this template use File | Settings | File Templates.
 */
public class Beggar extends AMovingObject {
    //===Fields==========================================================
    static private final Random random = new Random();
    private IFieldObject bottle = null;
    private BottleToMoneyHouse house = null;
    private IPathAlgorithm pathFinder = null;
    private  int range = 0;                     //
    private ICell target = null;
    //===/Fields=========================================================

    //===Constructors====================================================
    public Beggar(int range, IPathAlgorithm pathFinder) {
        this.range = range;
        this.pathFinder = pathFinder;
    }
    //===/Constructors===================================================

    //===Methods=========================================================
    private void findBottle() throws InvalidCoordinateException {
        HashSet<ICell> visited = new HashSet<ICell>();
        LinkedList<ICell> queue = new LinkedList<ICell>();

        visited.add(this.getCell());
        queue.add(this.getCell());

        while (!queue.isEmpty() && target == null) {
            ICell v = queue.removeFirst();

            if(!v.isEmpty()) {
                if(v.getFieldObject() instanceof Bottle) {
                    this.whenFindBottle((Bottle) v.getFieldObject());
                }
            }
            try{
                for(ICell u : getField().getCellNeighbors(v)) {
                    if(getField().distance(u,getCell()) <= range &&  visited.contains(u) == false) {
                        queue.addLast(u);
                        visited.add(u);
                    }
                }
            }catch(InvalidCoordinateException e) {
                throw new InvalidCoordinateException("Error in findBottle");
            }

        }
    }

    @Override
    public void makeAction() throws MakeActionException {
        try{
            ICell nextCell = null;
            if(target == null)
                findBottle();

            if(target != null) {
                if(pathFinder.findPath(getCell(), target))
                    nextCell = pathFinder.getNext(getCell(), target);
            }

            if(nextCell == null) {
                int n = random.nextInt(getField().getCellNeighbors(getCell()).size());
                nextCell = getField().getCellNeighbors(getCell()).get(n);
            }

            if(target == nextCell){
                if(target == house.getEntrance()) {
                    house.letIn(this);
                } else {
                    bottle = target.getFieldObject();
                    bottle.setCell(null);
                    target = house.getEntrance();
                }
            } else if(nextCell.isEmpty()) {
                setCell(nextCell);
            }
        } catch (CoordinateException e) {
            throw new MakeActionException("Error in Beggar.makeAction()", e);
        } catch (PathFindException e) {
            throw new MakeActionException("Error in Beggar.makeAction()", e);
        }

    }

    public void whenFindBottle(Bottle object) {
        target = object.getCell();
    }

    @Override
    public void getColliding(IMovingObject object) {
        object.processColliding(this);
    }

    @Override
    public char getSymbol() {
        return 'z';
    }
    //===/Methods========================================================

    //==Setters and getters==============================================
    public void setPathFinder(IPathAlgorithm pathFinder) {
        this.pathFinder = pathFinder;
    }

    public void setBottle( Bottle bottle) {
        this.bottle = bottle;
    }

    public void setHouse(BottleToMoneyHouse house) {
        this.house = house;
    }

    public void setTarget(ICell target) {
        this.target = target;
    }
    //==/Setters and getters=============================================
}
