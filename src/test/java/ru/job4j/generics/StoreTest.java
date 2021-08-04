package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

public class StoreTest {

    @Test
    public void whenAddUserThenGetReturnsAddedUser() {
        User user = new User("j123");
        User user2 = new User("j1234");
        UserStore store = new UserStore();
        store.add(user);
        store.add(user2);
        assertThat(store.findById("j1234"), is(user2));
    }

    @Test
    public void whenReplaceUserThenGetReturnsReplacedUser() {
        User user = new User("j123");
        User user2 = new User("j1234");
        User user3 = new User("j1235");
        UserStore store = new UserStore();
        store.add(user);
        store.add(user2);
        store.replace("j1234", user3);
        assertThat(store.findById("j1234"), is(user3));
    }

    @Test
    public void whenRemoveUserThenFindReturnsFalse() {
        User user = new User("j123");
        User user2 = new User("j1234");
        UserStore store = new UserStore();
        store.add(user);
        store.add(user2);
        assertThat(store.findById("j1234"), is(user2));
        store.delete("j1234");
        assertNull(store.findById("j1234"));
    }
}
