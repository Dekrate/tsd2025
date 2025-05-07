
package put.poznan.tsdcache.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import put.poznan.tsdcache.TestcontainersConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class TaskNo3Test {

    @Autowired
    private RedisTaskQueue taskQueue;

    @Test
    void shouldPushAndPopTasksInOrder() {
        //when
        taskQueue.push("alice", "task-1");
        taskQueue.push("alice", "task-2");
        //then
        assertThat(taskQueue.pop("alice")).isEqualTo("task-1");
        assertThat(taskQueue.pop("alice")).isEqualTo("task-2");
    }

    @Test
    void shouldReturnNullWhenQueueIsEmpty() {
        //when & then
        assertThat(taskQueue.pop("alice")).isNull();
    }

    @Test
    void shouldIsolateUserQueues() {
        //when
        taskQueue.push("alice", "alice-task");
        taskQueue.push("bob", "bob-task");
        //then
        assertThat(taskQueue.pop("alice")).isEqualTo("alice-task");
        assertThat(taskQueue.pop("bob")).isEqualTo("bob-task");
    }

    @Test
    void shouldClearUserQueue() {
        //when
        taskQueue.push("alice", "task-1");
        taskQueue.clear("alice");
        //then
        assertThat(taskQueue.pop("alice")).isNull();
    }
}
