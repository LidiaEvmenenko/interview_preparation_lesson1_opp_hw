package ru.geekbrains.hw1.task3;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException {
        Figure[] figure = {new Triangle("треугольник","красный",10.0,5.0,7.0,6.0),
                new Circle("круг","зеленый", 15.0),
                new Rectangle("квадрат", "синий", 13.0,13.0)
        };
        for (Figure f : figure) {
            System.out.println(f.color+" "+f.name+" имеет площадь "+f.square());
        }
    }
}
