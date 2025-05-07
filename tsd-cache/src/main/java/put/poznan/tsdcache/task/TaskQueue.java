package put.poznan.tsdcache.task;

public interface TaskQueue {
    void push(String user, String task);
    String pop(String user);
    void clear(String user);
}
