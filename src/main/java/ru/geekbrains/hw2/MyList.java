package ru.geekbrains.hw2;

public interface MyList {
    boolean hasNext(Object list);
    Object  get(int index);
    void add(Object value);
    void insert(int index, Object value);
    void delete(int index);
    Object next();
}
