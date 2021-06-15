package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator<T> implements Iterator<T> {
    private T[] array;
    private int cursor = 0;

    public EvenNumbersIterator(T[] array) {
        this.array = array;
    }


    @Override
    public boolean hasNext() {
        this.cursor = getEvenNumberCursor();
        return this.cursor < this.array.length;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return this.array[this.cursor++];
    }

    private int getEvenNumberCursor() {
        int tempCursor = cursor;
        while ((tempCursor < this.array.length) && ((int) this.array[tempCursor] % 2 == 1)) {
            tempCursor++;
        }
        return tempCursor;
    }
}
