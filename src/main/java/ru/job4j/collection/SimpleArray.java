package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] container;
    private int size = 0;
    private int modCount = 0;

    public SimpleArray() {
        this(10);
    }

    public SimpleArray(int initialLength) {
        container = new Object[initialLength];
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    public void add(T model) {
        modCount++;
        if(size == container.length) {
            container = grow();
        }
        container[size] = model;
        size++;
    }

    private Object[] grow() {
        return Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                if(modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) container[currentIndex++];
            }
        };
    }
}