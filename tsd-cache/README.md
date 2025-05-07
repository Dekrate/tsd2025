# Task 1: Implement Embedded Key-Value Storage for Bad Login Attempts

## 🎯 Goal

Implement an in-memory key-value storage to track bad login attempts and integrate it with an `Authenticator` class. This is useful for locking out users after too many failed login attempts.

---

## 📦 Part 1.1: Implement `Authenticator`

You will complete three private methods that rely on the `BadLoginAttemptsStorage`.

### ✅ Step-by-step

Implement the following methods:
- **`increaseBadLoginAttemptsCounter(String email)`**
- **`isLockedOut(String email)`**
- **`resetCounter(String email)`**

---

## 📦 Part 1.2: Implement `EmbeddedBadLoginAttemptsStorage`

This is a simple in-memory implementation using `HashMap`.

### ✅ Step-by-step

1. Create a field
```java
private final Map<String, Integer> storage = new HashMap<>();
```
2. Implement the following methods:
- **`get(String key)`**
- **`put(String key, Integer value)`**
- **`increment(String key)`**
- **`remove(String key)`**

---

## 🧪 Run tests to check your solution

---

# Task 2: Implement Redis-Based Key-Value Storage for Bad Login Attempts

## 🎯 Goal

Implement a Redis-backed key-value storage to track bad login attempts with an automatic expiration (TTL). This implementation uses `RedisTemplate` and `ValueOperations` to interact with Redis.

---

## 📦 Part 2.1: Implement `RedisBadLoginAttemptsStorage`

You will complete methods to interact with Redis. Key operations should include **get**, **put**, **increment**, **remove**, and setting TTL.

### ✅ Step-by-step

Implement the following methods:
- **`get(String key)`**
- **`put(String key, Integer value)`**
- **`increment(String key)`**
- **`remove(String key)`**
- **`setTtl(String key)`**

---

## 🧪 Run tests to check your solution

---

# Task 3: Implement Redis-Based Task Queue Using Redis Lists

## 🎯 Goal

Implement a Redis-based task queue where tasks are associated with a user. The queue will support operations to **push** tasks, **pop** tasks, and **clear** tasks for a given user. Redis will be used to store tasks in a list.

---

## 📦 Part 3.1: Implement `RedisTaskQueue`

You will implement three methods: **push**, **pop**, and **clear**. The key operations will use Redis **lists** to enqueue and dequeue tasks.

### ✅ Step-by-step

Implement the following methods:
- **`push(String user, String task)`**
- **`pop(String user)`**
- **`clear(String user)`**

---

## 🧪 Run tests to check your solution

---