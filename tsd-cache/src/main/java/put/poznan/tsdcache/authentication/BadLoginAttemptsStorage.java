package put.poznan.tsdcache.authentication;

interface BadLoginAttemptsStorage {
    Integer get(String key);
    void put(String key, Integer value);
    void increment(String key);
    void remove(String key);
}
