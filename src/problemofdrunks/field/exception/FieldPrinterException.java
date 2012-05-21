package problemofdrunks.field.exception;

import com.sun.corba.se.impl.presentation.rmi.ExceptionHandlerImpl;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 20.05.12
 * Time: 16:24
 * To change this template use File | Settings | File Templates.
 */
public class FieldPrinterException extends Exception {
    public FieldPrinterException() {}
    public FieldPrinterException(String message) {
        super(message);
    }
    public FieldPrinterException(String message, Throwable cause) {
        super(message, cause);
    }
}
