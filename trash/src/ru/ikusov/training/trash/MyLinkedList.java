package ru.ikusov.training.trash;

import java.util.Arrays;
import java.util.List;

public class MyLinkedList<T> {

    private Entry<T> header;
    private int size;

    public MyLinkedList() {
        header = new Entry<>(null);
        header.next = header.prev = header;
        size = 0;
    }

    public int size() {
        return size;
    }

    public String toString() {
        String s = "";
        for (int i=0; i<size; i++)
            s += (entry(i).element + " ");
        return s;
    }

    public void add(T value) {
        Entry<T> newEntry = new Entry<>(value, header, header.prev);
        newEntry.prev.next = newEntry;
        newEntry.next.prev = newEntry;
        size++;
    }

    public T get(int index) {
        Entry<T> currentEntry = header;
        for (int i=0; i<=index; i++) {
            currentEntry = currentEntry.next;
        }
        return currentEntry.element;
    }

    public void remove(int index) {
        Entry<T> currentEntry = header;
        for (int i=0; i<=index; i++) {
            currentEntry = currentEntry.next;
        }
        currentEntry.prev.next = currentEntry.next;
        currentEntry.next.prev = currentEntry.prev;
        currentEntry.next = currentEntry.prev = null;
        currentEntry = null;
        size--;
    }

    private Entry<T> entry(int index) {
        Entry<T> e = header;
        for (int i=0; i<=index; i++) {
            e = e.next;
        }
        return e;
    }

    private class Entry<E> {
        E element;
        Entry<E> prev;
        Entry<E> next;

        Entry(E element, Entry<E> next, Entry<E> prev) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        Entry(E element) {
            this.element = element;
        }
    }
}