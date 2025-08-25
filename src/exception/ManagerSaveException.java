package exception;

public class ManagerSaveException extends RuntimeException {
    public ManagerSaveException(String message, Exception cause) {
        super(message, cause);
    }

    public String getDetailMassage() {
        return getMessage() + getCause();
    }

}
