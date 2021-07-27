package ru.job4j.generics;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleArrayTest {

    @Test
    public void whenAddOneElementThenGetFirstReturnsThatElement(){
        SimpleArray<String> sa = new SimpleArray<>(5);
        sa.add("Samsung");
        assertThat(sa.get(0), is("Samsung"));
    }

    @Test
    public void whenRemove(){
        SimpleArray<String> sa = new SimpleArray<>(5);
        sa.add("Samsung");
        sa.add("Pixel");
        sa.add("Huawei");
        sa.remove(1);
        assertThat(sa.get(0), is("Samsung"));
        assertThat(sa.get(1), is("Huawei"));
    }

    @Test
    public void whenSet(){
        SimpleArray<String> sa = new SimpleArray<>(5);
        sa.add("Samsung");
        sa.add("Pixel");
        sa.add("Huawei");
        sa.set(2, "OnePlus");
        assertThat(sa.get(0), is("Samsung"));
        assertThat(sa.get(1), is("Pixel"));
        assertThat(sa.get(2), is("OnePlus"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        SimpleArray<String> array = new SimpleArray<>(5);
        array.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        SimpleArray<String> array = new SimpleArray<>(5);
        array.add("first");
        array.get(1);
    }

    @Test
    public void whenIterate(){
        SimpleArray<String> sa = new SimpleArray<>(10);
        sa.add("Samsung");
        sa.add("Pixel");
        sa.add("Huawei");
        Iterator<String> it = sa.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Samsung"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Pixel"));
        assertThat(it.next(), is("Huawei"));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleArray<String> array = new SimpleArray<>(10);
        array.iterator().next();
    }

}
