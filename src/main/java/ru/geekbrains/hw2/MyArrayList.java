package ru.geekbrains.hw2;

import java.lang.reflect.Array;

public class MyArrayList<T> implements MyList {
    private final int INIT_CAPACITY = 2;
    private int capacity = INIT_CAPACITY;
    private T[] list = null;
    private Class<T> clazz;
    private int pointer = 0;
    private int size;

    public MyArrayList(Class<T> clazz) {
        this.clazz = clazz;
        list = (T[]) Array.newInstance(clazz, INIT_CAPACITY);
        this.size = 0;
    }

    @Override
    public boolean hasNext(Object list) {
        return pointer >= this.list.length;
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            return (T) list[index];
        } else
            throw new IndexOutOfBoundsException("Index " + index + " incorrect");
    }

    @Override
    public void add(Object value) {
        if (size < capacity) {
            pointer = size;
            list[pointer] = (T) value;
            size++;
        } else {
            capacity = capacity * 2;
            resize(capacity);
            add(value);
        }
    }

    @Override
    public void insert(int index, Object value) {
        if (index >= 0 && index < size) {
            if (index >= size) add(value);
            else {
                if (size == capacity) {
                    capacity = capacity * 2;
                    resize(capacity);
                }
                pointer = size;
                for (int i = pointer; i > index; i--) {
                    list[i] = list[i - 1];
                }
                list[index] = (T) value;
                size++;

            }
        } else
            throw new IndexOutOfBoundsException("Index " + index + " incorrect");
    }

    @Override
    public void delete(int index) {
        if (index >= 0 && index < size) {
            if (index != size - 1) {
                for (int i = index + 1; i < size; i++)
                    list[i - 1] = list[i];
            }
            list[size - 1] = null;
            size--;

        } else
            throw new IndexOutOfBoundsException("Index " + index + " incorrect");
    }


    @Override
    public T next() {
        return (T) list[pointer];
    }

    private void resize(int newLength) {
        T[] newList = (T[]) Array.newInstance(clazz, newLength);
        System.arraycopy(list, 0, newList, 0, size);
        list = newList;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < list.length; i++) {
            builder.append(list[i]).append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("]");
        return builder.toString();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
