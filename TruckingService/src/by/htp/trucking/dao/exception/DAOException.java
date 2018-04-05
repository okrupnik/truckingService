package by.htp.trucking.dao.exception;

public class DAOException extends Exception {
	
	public DAOException() {
        super();
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Exception cause) {
        super(message, cause);
    }

    public DAOException(Exception cause) {
        super(cause);
    }
}
