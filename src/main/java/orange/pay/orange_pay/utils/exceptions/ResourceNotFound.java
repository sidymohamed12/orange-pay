package orange.pay.orange_pay.utils.exceptions;

public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound() {
        super("404 - Resource not found on the server");
    }

    public ResourceNotFound(String message) {
        super("404 - " + message);
    }
}
