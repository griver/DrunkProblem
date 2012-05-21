package problemofdrunks.field.utils;

import problemofdrunks.field.exception.FieldPrinterException;
import problemofdrunks.field.exception.InvalidCoordinateException;
import problemofdrunks.field.ICell;
import problemofdrunks.field.IField;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 07.04.12
 * Time: 21:23
 * To change this template use File | Settings | File Templates.
 */
public class FieldPrinter {
    public static void print(IField field) throws FieldPrinterException {
       int[] sizes = field.getSizes();
        if(sizes.length != 2) {
            System.out.println("Не могу отображать поля размерности: " + Integer.toString(sizes.length));
            return;
        }
        try{

            ICell cell;
            for(int i=0; i<sizes[0]; i++) {
                for (int j = 0; j<sizes[1]; j++) {
                    System.out.print(field.getCell(i, j).getSymbol());

                }
                System.out.println();
            }
        }catch (InvalidCoordinateException e) {
            throw  new FieldPrinterException("Error in loop running through the cells", e);
        }
    }
}
