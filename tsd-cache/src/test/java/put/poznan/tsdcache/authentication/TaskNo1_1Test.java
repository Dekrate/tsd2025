package put.poznan.tsdcache.authentication;

import org.junit.jupiter.api.Test;
import put.poznan.tsdcache.authentication.exceptions.UserLockedOutException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class TaskNo1_1Test {

    private final PasswordChecker passwordChecker = mock(PasswordChecker.class);
    private final BadLoginAttemptsStorage badLoginAttemptsStorage = mock(BadLoginAttemptsStorage.class);
    private final Authenticator sut = new Authenticator(badLoginAttemptsStorage, passwordChecker, 3);

    @Test
    void shouldIncreaseCounterIfPasswordIsIncorrect() {
        //given
        String email = "test@test.com";
        String password = "password";
        when(badLoginAttemptsStorage.get(email)).thenReturn(0);
        when(passwordChecker.check(email, password)).thenReturn(false);
        //when
        sut.authenticate(email, password);
        //then
        verify(badLoginAttemptsStorage).increment(email);
    }

    @Test
    void shouldNotIncreaseCounterIfPasswordIsCorrect() {
        //given
        String email = "test@test.com";
        String password = "password";
        when(badLoginAttemptsStorage.get(email)).thenReturn(0);
        when(passwordChecker.check(email, password)).thenReturn(true);
        //when
        sut.authenticate(email, password);
        //then
        verify(badLoginAttemptsStorage, never()).increment(email);
    }

    @Test
    void shouldThrowIfLockedOut() {
        //given
        String email = "test@test.com";
        String password = "password";
        //when & then
        when(passwordChecker.check(email, password)).thenReturn(true);
        when(badLoginAttemptsStorage.get(email)).thenReturn(4);
        assertThatThrownBy(() -> sut.authenticate(email, password)).isInstanceOf(UserLockedOutException.class);
    }

    @Test
    void shouldResetCounterIfPasswordIsCorrect() {
        //given
        String email = "test@test.com";
        String password = "password";
        when(badLoginAttemptsStorage.get(email)).thenReturn(3);
        when(passwordChecker.check(email, password)).thenReturn(true);
        //when
        sut.authenticate(email, password);
        //then
        verify(badLoginAttemptsStorage).remove(email);
    }
}