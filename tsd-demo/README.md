# Java + Spring introduction

## 🎯 Task 1

This tutorial will guide you through completing the `User` class. You will learn some about `Optional<>`, constructors and Factory Methods.


### Task 1.1: Finish the first Factory Method

#### What you need to do:
- Implement the first factory method.
- It should **create and return a new `User` object**.

---

### Task 1.2: Make `email` Optional


Some users do not have email address. Thus, we want to make this parameter optional.
You must modify the `email` field and related methods. Utilise `Optional<String>` type.
Remember that the constructor also needs to handle such cases. Try to use `Optional.ofNullable()`.
Moreover, also email getter needs to be modified. Let's explore `Optional` methods, such as `.orElse()`.
Please return a default company address if the user has no private email address.

---

### Task 1.3: Create a second Factory Method (without email)

Now it is time to create yet another Factory Method - this time without email address in parameters.

---

## ✅ Checkpoint
Run `TaskNo1Test` to verify your progress

---

## 🎯 Task 2: Implement Filtering Logic

You need to implement the method `filter(Collection<User> users)` so that it:
- **Removes users whose name starts with the letter 'A'** (case-sensitive).
- **Removes users with invalid email addresses**.
- **Removes users whose phone number is not Polish** (Polish numbers start with `+48`).

Please try to keep the convention and explore Java Streams.

---

## ✅ Checkpoint

Run `TaskNo2Test` to verify your progress

---

## 🎯 Task 3: Your first endpoint

It is time to create your first endpoint.

---

### Task 3.1: Create REST Controller

You need to tell Spring that the class `UserEndpoint` handles HTTP requests. Let's use `@RestController` annotation.

---

### Task 3.2: Map Method to HTTP POST and Bind Request Body

You need to map the `createUser` method to an HTTP `POST` request and read the `User` object from the request body.

#### How:
- Add `@PostMapping("/users")` above the method.
- Add `@RequestBody` to the method parameter.

---

### Task 3.3: Add User and Return ID

You need to:
- Add the new `User` to the `users` list.
- Return a response with HTTP 200 OK and a JSON object containing the user ID.

For simplicity, you can simulate a generated ID using i.e. `users.size()`.

---

## ✅ Checkpoint

Run `TaskNo3Test` to verify your progress

---