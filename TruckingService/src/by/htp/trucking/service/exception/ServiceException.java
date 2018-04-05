package by.htp.trucking.service.exception;

public class ServiceException extends Exception {
	
	public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Exception cause) {
        super(message, cause);
    }

    public ServiceException(Exception cause) {
        super(cause);
    }

}
