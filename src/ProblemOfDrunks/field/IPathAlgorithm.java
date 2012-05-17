package ProblemOfDrunks.field;


import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 19.03.12
 * Time: 3:07
 * To change this template use File | Settings | File Templates.
 */
public interface IPathAlgorithm {
    boolean  findPath(ICell startCell, ICell endCell );


    List<ICell> getPath(ICell startCell, ICell endCell);

    ICell getNext(ICell startCell, ICell endCell);

    void setField(IField field);
}
