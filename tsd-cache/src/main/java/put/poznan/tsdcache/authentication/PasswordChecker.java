package put.poznan.tsdcache.authentication;

import java.util.Random;

class PasswordChecker {

    boolean check(String email, String password) {
        return new Random().nextBoolean();
    }
}
