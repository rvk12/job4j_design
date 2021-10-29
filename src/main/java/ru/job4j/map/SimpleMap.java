package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count > capacity * LOAD_FACTOR) {
            expand();
        }
        int index = indexFor(hash(key));
        if (table[index] != null) {
            return false;
        }
        table[index] = new MapEntry<>(key, value);
        ++modCount;
        ++count;
        return true;
    }

    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        int h = key.hashCode();
        return (h) ^ (h >>> 16);
    }

    private int indexFor(int hash) {
        return (table.length - 1) & hash;
    }

    private void expand() {
        MapEntry<K, V>[] oldTable = table;
        int oldCapacity = (oldTable == null) ? 0 : oldTable.length;
        int newCapacity = oldCapacity * 2;
        MapEntry<K, V>[] newTable = new MapEntry[newCapacity];
        for (int i = 0; i < oldCapacity; i++) {
            if (oldTable[i] != null) {
                newTable[(newCapacity - 1) & hash(oldTable[i].key)] = oldTable[i];
                oldTable[i] = null;
            }
        }
        capacity = newCapacity;
        table = newTable;
    }

    @Override
    public V get(K key) {
        MapEntry<K, V> me = table[indexFor(hash(key))];
        return (me == null) ? null : me.value;
    }

    @Override
    public boolean remove(K key) {
        table[indexFor(hash(key))] = null;
        ++modCount;
        --count;
        return true;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int index = 0;
            private int expectedModCount = modCount;

            public void iterate() {
                if (table != null && count > 0) {
                    while (index < table.length && table[index] == null) {
                        index++;
                    }
                }
            }

            @Override
            public boolean hasNext() {
                iterate();
                return table[index] != null;
            }

            @Override
            public K next() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}