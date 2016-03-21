package pv168.common;


public class ServiceFailureException extends RuntimeException {


private static final long serialVersionUID = 1L;

public ServiceFailureException(String msg) {
        super(msg);
}

public ServiceFailureException(Throwable cause) {
        super(cause);
}

public ServiceFailureException(String message, Throwable cause) {
        super(message, cause);
}

}
