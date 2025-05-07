package put.poznan.tsdcache.authentication;

import lombok.RequiredArgsConstructor;
import put.poznan.tsdcache.authentication.exceptions.BadCredentialsException;
import put.poznan.tsdcache.authentication.exceptions.UserLockedOutException;

@RequiredArgsConstructor
public class Authenticator {

    private final BadLoginAttemptsStorage badLoginAttemptsStorage;
    private final PasswordChecker passwordChecker;
    // INFO: Threshold is set in configuration class
    private final Integer badLoginAttemptsThreshold;

    void authenticate(String email, String password) {
        if (isLockedOut(email)) {
            throw new UserLockedOutException("User " + email + " is locked out");
        }
        if (!isPasswordCorrect(email, password)) {
            increaseBadLoginAttemptsCounter(email);
//            throw new BadCredentialsException("Bad credentials");
        }
        resetCounter(email);
    }

    boolean isPasswordCorrect(String email, String password) {
        return passwordChecker.check(email, password);
    }

    // TODO 1.1 - Finish this method using badLoginAttemptsStorage
    // TODO - TIP: Use email as a key
    private void increaseBadLoginAttemptsCounter(String email) {
        badLoginAttemptsStorage.increment(email);
    }

    // TODO 1.1 - Finish this method using badLoginAttemptsStorage and badLoginAttemptsThreshold
    private boolean isLockedOut(String email) {
        return badLoginAttemptsStorage.get(email) > badLoginAttemptsThreshold;
    }

    // TODO 1.1 - Finish this method using badLoginAttemptsStorage
    private void resetCounter(String email) {
        badLoginAttemptsStorage.remove(email);
    }
}
