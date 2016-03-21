package pv168.common;


public class IllegalEntityException extends RuntimeException {

/**
 *
 */
private static final long serialVersionUID = 1L;

/**
 * Creates a new instance of
 * <code>IllegalEntityException</code> without detail message.
 */
public IllegalEntityException() {
}

/**
 * Constructs an instance of
 * <code>IllegalEntityException</code> with the specified detail
 * message.
 *
 * @param msg the detail message.
 */
public IllegalEntityException(String msg) {
        super(msg);
}

/**
 * Constructs an instance of
 * <code>IllegalEntityException</code> with the specified detail
 * message and cause.
 *
 * @param message the detail message.
 * @param cause the cause
 */
public IllegalEntityException(String message, Throwable cause) {
        super(message, cause);
}

}
