package org.example.tsddemo;

import java.util.Optional;

public class User {
    private final String name;
    private final String password;
    //TODO - Task 1.2 - Change email field type so it can handle users without email address
    private final Optional<String> email;
    private final String phone;

    //TODO - Task 1.2 - You need to modify constructor to handle Users without emails
    public User(String name, String password, String email, String phone) {
        this.name = name;
        this.password = password;
        this.email = Optional.ofNullable(email);
        this.phone = phone;
    }

    //TODO - Task 1.1 - Finish factory method
    static User of(String name, String password, String email, String phone) {
        return new User(name, password, email, phone);
    }

    //TODO - Task 1.3 - Finish another factory method, so it is possible to create User without email
    // Metoda fabryczna tworząca użytkownika bez emaila
    static User of(String name, String password, String phone) {
        return new User(name, password, null, phone);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    //TODO - Task 1.2 - Modify getter, so it returns company email if user has no email address
    public String getEmail() {
        return this.email.orElse("default@company.com");
    }

    public String getPhone() {
        return phone;
    }
}