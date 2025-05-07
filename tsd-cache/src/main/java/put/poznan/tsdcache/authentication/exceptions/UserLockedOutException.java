package put.poznan.tsdcache.authentication.exceptions;

public class UserLockedOutException extends RuntimeException {
    public UserLockedOutException(String message) {
        super(message);
    }
}
