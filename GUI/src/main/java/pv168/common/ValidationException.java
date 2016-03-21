package pv168.common;


public class ValidationException extends RuntimeException {


private static final long serialVersionUID = 1L;

/**
 * Creates a new instance of
 * <code>ValidationException</code> without detail message.
 */
public ValidationException() {
}

/**
 * Constructs an instance of
 * <code>ValidationException</code> with the specified detail message.
 *
 * @param msg the detail message.
 */
public ValidationException(String msg) {
        super(msg);
}
}
