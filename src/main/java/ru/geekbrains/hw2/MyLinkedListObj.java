package ru.geekbrains.hw2;

class MyLinkedListObj<T> {
    private MyLinkedListObj<T> prev;
    private T value;
    private MyLinkedListObj<T> next;

    public MyLinkedListObj(MyLinkedListObj<T> prev, T value, MyLinkedListObj<T> next) {
        this.prev = prev;
        this.value = value;
        this.next = next;
    }

    public MyLinkedListObj<T> getPrev() {
        return prev;
    }

    public void setPrev(MyLinkedListObj<T> prev) {
        this.prev = prev;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public MyLinkedListObj<T> getNext() {
        return next;
    }

    public void setNext(MyLinkedListObj<T> next) {
        this.next = next;
    }
}
