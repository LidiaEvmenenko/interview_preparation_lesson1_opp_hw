package ru.geekbrains.hw2;

public class Main {
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList();

        list.add(23);
        list.add(44);
        list.insert(1, 17);
        System.out.println(list);
        System.out.println(list.get(1));
        list.insert(2, 68);
        list.insert(2, 90);
        System.out.println(list);
        list.delete(3);
        System.out.println(list);
        System.out.println(list.size());
        System.out.println("------------");

        Class<String> clazz = String.class;
        MyArrayList<String> list1 = new MyArrayList<>(clazz);
        list1.add("G");
        list1.add("I");
        list1.insert(1,"P");
        list1.add("T");
        System.out.println(list1.size());
        list1.delete(2);
        System.out.println(list1.toString());
        System.out.println(list1.size());
        list1.insert(1, "F");
        System.out.println(list1.toString());
        System.out.println(list1.size());
        list1.insert(3, "O");
        System.out.println(list1.toString());
        System.out.println(list1.size());

    }
}
