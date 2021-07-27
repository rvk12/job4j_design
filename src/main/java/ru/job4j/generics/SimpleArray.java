package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private final T[] container;
    private int length = 0;

    public SimpleArray(int length) {
        container = (T[]) new Object[length];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < length && container[currentIndex] != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[currentIndex++];
            }
        };
    }

    public void add(T model) {
        container[length] = model;
        length++;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, length);
        container[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, length);
        System.arraycopy(container, index + 1, container, index, length - index);
    }

    public T get(int index) {
        Objects.checkIndex(index, length);
        return container[index];
    }
}
