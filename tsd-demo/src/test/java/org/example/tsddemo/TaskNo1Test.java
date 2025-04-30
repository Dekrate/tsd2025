package org.example.tsddemo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TaskNo1Test {

    @Test
    void shouldReturnDefaultEmail() {
        //given
        User testUser = new User("Andrzej", "Password", null, "+48123456789");
        //when & then
        assertThat(testUser.getEmail()).isNotNull();
    }

    @Test
    void shouldReturnProvidedEmail() {
        //given
        String email = "andrzej@example.org";
        User testUser = new User("Andrzej", "Password", email, "+48123456789");
        //when & then
        assertThat(testUser.getEmail()).isEqualTo(email);
    }

    @Test
    void shouldCreateUserWithEmailUsingFactoryMethod() {
        //given
        String email = "andrzej@example.org";
        String name = "Andrzej";
        String password = "Password";
        String phone = "+48123456789";
        User testUser = User.of(name, password, email, phone);
        //when & then
        assertThat(testUser.getEmail()).isEqualTo(email);
        assertThat(testUser.getName()).isEqualTo(name);
        assertThat(testUser.getPassword()).isEqualTo(password);
        assertThat(testUser.getPhone()).isEqualTo(phone);
    }

    @Test
    void shouldCreateUserWithoutEmailUsingFactoryMethod() {
        //given
        String name = "Andrzej";
        String password = "Password";
        String phone = "+48123456789";
        User testUser = User.of(name, password, phone);
        //when & then
        assertThat(testUser.getEmail()).isNotNull();
        assertThat(testUser.getName()).isEqualTo(name);
        assertThat(testUser.getPassword()).isEqualTo(password);
        assertThat(testUser.getPhone()).isEqualTo(phone);
    }
}