package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class SimpleMapTest {

    @Test
    public void whenPutNotNullKey() {
        SimpleMap<String, String> sm = new SimpleMap<>();
        assertThat(sm.put("testk", "testval"), is(true));
    }

    @Test
    public void whenPutNullKey() {
        SimpleMap<String, String> sm = new SimpleMap<>();
        assertThat(sm.put(null, "testval"), is(true));
    }

    @Test
    public void whenPutTenKeys() {
        SimpleMap<String, String> sm = new SimpleMap<>();
        sm.put("testk", "test");
        sm.put("testk2", "test2");
        sm.put("testk3", "test3");
        sm.put("testk4", "test4");
        sm.put("testk5", "test5");
        sm.put("testk6", "test6");
        sm.put("testk7", "test7");
        sm.put("testk8", "test8");
        sm.put("testk9", "test9");
        assertThat(sm.put("testk10", "testval10"), is(true));
    }

    @Test
    public void getExistingKey() {
        SimpleMap<String, String> sm = new SimpleMap<>();
        sm.put("testk", "testval");
        sm.put("testk2", "testval2");
        sm.put("testk4", "testval3");
        sm.put("testk5", "testval4");
        assertThat(sm.get("testk2"), is("testval2"));
    }

    @Test
    public void getNullKey() {
        SimpleMap<String, String> sm = new SimpleMap<>();
        sm.put("testk", "testval");
        sm.put(null, "testval2");
        assertThat(sm.get(null), is("testval2"));
    }

    @Test
    public void getNotExistingKey() {
        SimpleMap<String, String> sm = new SimpleMap<>();
        sm.put("testk", "testval");
        sm.put("testk2", "testval2");
        assertNull(sm.get("testk3"));
    }

    @Test
    public void removeExistingKey() {
        SimpleMap<String, String> sm = new SimpleMap<>();
        sm.put("testk", "testval");
        sm.put("testk2", "testval2");
        assertThat(sm.remove("testk"), is(true));
        assertNull(sm.get("testk"));
    }

    @Test
    public void removeNullKey() {
        SimpleMap<String, String> sm = new SimpleMap<>();
        sm.put("testk", "testval");
        sm.put(null, "testval2");
        assertThat(sm.remove(null), is(true));
        assertNull(sm.get(null));
    }

    @Test
    public void iteratorHasNext() {
        SimpleMap<String, String> sm = new SimpleMap<>();
        sm.put("testk", "testval");
        sm.put("testk2", "testval2");
        Iterator<String> it = sm.iterator();
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void iteratorHasNotNext() {
        SimpleMap<String, String> sm = new SimpleMap<>();
        Iterator<String> it = sm.iterator();
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void iteratorConcurrentModification() {
        SimpleMap<String, String> sm = new SimpleMap<>();
        sm.put("testk", "testval");
        Iterator<String> it = sm.iterator();
        sm.put("testk2", "testval2");
        it.next();
    }
}