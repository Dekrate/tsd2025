package put.poznan.tsdcache.authentication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TaskNo1_2Test {

    private EmbeddedBadLoginAttemptsStorage storage;

    @BeforeEach
    void setUp() {
        storage = new EmbeddedBadLoginAttemptsStorage();
    }

    @Test
    void shouldReturnZeroWhenKeyDoesNotExist() {
        //when
        assertThat(storage.get("user1")).isZero();
    }

    @Test
    void shouldReturnValueAfterPut() {
        //given
        storage.put("user1", 5);
        //when & then
        assertThat(storage.get("user1")).isEqualTo(5);
    }

    @Test
    void shouldIncrementValueWhenKeyIsAbsent() {
        //given
        storage.increment("user1");
        //when & then
        assertThat(storage.get("user1")).isEqualTo(1);
    }

    @Test
    void shouldIncrementExistingValue() {
        //given
        storage.put("user1", 2);
        storage.increment("user1");
        //when & then
        assertThat(storage.get("user1")).isEqualTo(3);
    }

    @Test
    void shouldRemoveKey() {
        // given
        storage.put("user1", 4);
        storage.remove("user1");
        // when & then
        assertThat(storage.get("user1")).isZero();
    }

}