package ProblemOfDrunks.field.impl;

import ProblemOfDrunks.field.IPathAlgorithm;
import ProblemOfDrunks.field.exception.InvalidCoordinateException;
import ProblemOfDrunks.field.ICell;
import ProblemOfDrunks.field.IField;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 19.03.12
 * Time: 3:15
 * To change this template use File | Settings | File Templates.
 */
public class LeeAlgorithm implements IPathAlgorithm {
    //===Fields==========================================================
    private ICell[] queue;
    private IField  field;
    private LinkedList<ICell> path = new LinkedList<ICell>();
    //===/Fields=========================================================

    //===Constructors====================================================
    public LeeAlgorithm(IField field) {
        this.setField(field);
    }
    //===/Constructors===================================================

    //===Methods=========================================================
    @Override
    public boolean findPath(ICell startCell, ICell endCell) {
        path.clear();
        HashMap<ICell, ICell> visited = new HashMap<ICell, ICell>();

        int head = 0;
        int tail = 1;

        queue[head] = startCell;
        visited.put(startCell, null);

        while(head != tail) {
            if(queue[head] == endCell)
                break;
            try{
                for(ICell cell : field.getCellNeighbors(queue[head])) {
                    if(visited.containsKey(cell) == false) {
                        if(cell.isEmpty() || cell == endCell){
                            visited.put(cell, queue[head]);
                            queue[tail++] = cell;
                        }
                    }
                }
            }catch (InvalidCoordinateException e) {
                e.printStackTrace(System.err);
                System.exit(0);
            }
            ++head;
        }

        //path building
        if(visited.containsKey(endCell) == false ) {
            return false;
        }


        ICell parent = endCell;

        while(parent != null) {
            path.addFirst(parent);
            parent = visited.get(parent);
        }
        return true;
    }

    @Override
    public ICell getNext(ICell startCell, ICell endCell) {
        if(path.isEmpty())
            return null;

        if(path.getFirst() == endCell) {
            return path.removeFirst();
        }

        if(path.getFirst() == startCell) {
            if(path.getLast() == endCell) {
                path.removeFirst();
                return path.getFirst();
            }
        }
        return null;
    }

    @Override
    public List<ICell> getPath(ICell startCell, ICell endCell) {
        this.findPath(startCell, endCell);
        return this.path;
    }
    //===/Methods========================================================

    //==Setters and getters==============================================
    public IField getField() {
        return field;
    }

    public void setField(IField field) {
        this.field = field;
        this.queue = new ICell[field.getNumberOfCells()];
    }
    //==/Setters and getters=============================================
}
