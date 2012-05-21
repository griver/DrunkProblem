package problemofdrunks.field;


import problemofdrunks.field.exception.InvalidPathArgumentException;
import problemofdrunks.field.exception.PathFindException;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 19.03.12
 * Time: 3:07
 * To change this template use File | Settings | File Templates.
 */
public interface IPathAlgorithm {
    boolean  findPath(ICell startCell, ICell endCell ) throws PathFindException, InvalidPathArgumentException;

    List<ICell> getPath(ICell startCell, ICell endCell) throws PathFindException, InvalidPathArgumentException;

    ICell getNext(ICell startCell, ICell endCell) throws PathFindException, InvalidPathArgumentException;

    void setField(IField field);
}
