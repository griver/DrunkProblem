package ProblemOfDrunks.objects.things;

import ProblemOfDrunks.field.ICell;
import ProblemOfDrunks.field.IField;
import ProblemOfDrunks.objects.IFieldObject;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 07.04.12
 * Time: 15:14
 * To change this template use File | Settings | File Templates.
 */
public abstract  class AFieldObject implements IFieldObject {
    private ICell cell;
    private IField field;

    @Override
    public ICell getCell() {
        return cell;
    }

    @Override
    public void setCell(ICell cell) {
        if(this.cell != null) {
            this.cell.setFieldObject(null);
        }

        this.cell = cell;

        if(this.cell != null) {
            this.cell.setFieldObject(this);
        }
    }

    public IField getField() {
        return field;
    }

    public void setField(IField field) {
        this.field = field;
    }

}
