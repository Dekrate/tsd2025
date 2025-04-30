package org.example.tsddemo;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class TaskNo2Test {

    private static final User USER_1 = new User("Andrzej", "Password", "andrzej@example.com", "+48508624734"); // Starts with letter A
    private static final User USER_2 = new User("Anna", "Secret", "anna@example.com", "+48512345678"); // Starts with letter A
    private static final User USER_3 = new User("Bartek", "Password123", "bartek@example.com", "+48765432109");
    private static final User USER_4 = new User("Zofia", "Passw0rd", "zofia[at]example.com", "+48598765432"); // Invalid email
    private static final User USER_5 = new User("Marek", "M@rek", "marek@example.com", "+491512345678"); // Non-Polish number

    private static final Set<User> users = Set.of(
            USER_1,
            USER_2,
            USER_3,
            USER_4,
            USER_5
    );

    private final UserFilter sut = new UserFilter();

    @Test
    void shouldFilterUsers() {
        //when
        Collection<User> filteredUsers = sut.filter(users);
        //then
        assertThat(filteredUsers).isEmpty();
    }
}