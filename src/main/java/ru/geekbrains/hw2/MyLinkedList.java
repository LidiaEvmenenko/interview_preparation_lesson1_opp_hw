package ru.geekbrains.hw2;

public class MyLinkedList<T> implements MyList {
    private MyLinkedListObj<T> front;
    private int size;

    public MyLinkedList() {
        this.size = 0;
    }

    @Override
    public boolean hasNext(Object list) {
        MyLinkedListObj<T> temp = (MyLinkedListObj<T>) list;
        if (temp.getNext() != null) return true;
        return false;
    }

    @Override
    public MyLinkedListObj next() {
        return front.getNext();
    }

    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        if (isEmpty() || index > size - 1)
            throw new ArrayIndexOutOfBoundsException("Index " + index + " not found");
        if (index == 0 )
            return front.getValue();
        MyLinkedListObj<T> temp = front;
        int i = 0;
        while (i < index){
            temp = temp.getNext();
            i++;
        }
        return temp.getValue();
    }

    @Override
    public void add(Object value) {
        if (size == 0) addFirst(value);
        else  addEnd(value);

    }

    private void addFirst(Object value) {
        if (isEmpty()) {
            front = new MyLinkedListObj<T>(null, (T) value, null);
        } else {
            MyLinkedListObj<T> temp = front;
            front = new MyLinkedListObj<T>(null, (T) value, temp);
            temp.setPrev(front);
        }
        size++;
    }

    private void addEnd(Object value) {
        if (isEmpty()) {
            front = new MyLinkedListObj<T>(null, (T) value, null);
        } else {
            MyLinkedListObj<T> temp = front;
            while (hasNext(temp)) {
                temp = temp.getNext();
            }
            temp.setNext(new MyLinkedListObj<T>(temp, (T) value, null));
        }
        size++;
    }

    @Override
    public void insert(int index, Object value) {
        if (index > size - 1)
            throw new ArrayIndexOutOfBoundsException("Index " + index + " not found");
        MyLinkedListObj<T> temp = front;
        int i = 0;
        while (i < index){
            temp = temp.getNext();
            i++;
        }

        MyLinkedListObj<T> newList = new MyLinkedListObj<>(temp.getPrev(), (T) value, temp);
        if (temp.getPrev() != null)
            temp.getPrev().setNext(newList);
        else
            front = newList;
        temp.setPrev(newList);
        size++;
    }

    @Override
    public void delete(int index) {
        if (isEmpty() || index > size - 1)
            throw new ArrayIndexOutOfBoundsException("Index " + index + " not found");
        if (index == 0) {
            front = front.getNext();
            return;
        }
        MyLinkedListObj<T> temp = front;
        int i = 0;
        while (i < index){
            temp = temp.getNext();
            i++;
        }

        if (temp.getNext() != null)
            temp.getNext().setPrev(temp.getPrev());
        temp.getPrev().setNext(temp.getNext());
        size--;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public String toString() {
        MyLinkedListObj<T> temp = front;
        StringBuilder builder = new StringBuilder("[");

        while (temp != null) {
            builder.append(temp.getValue()).append(",");
            temp = temp.getNext();
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("]");
        return builder.toString();
    }
}
