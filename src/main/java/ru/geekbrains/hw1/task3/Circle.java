package ru.geekbrains.hw1.task3;

public class Circle extends Figure{
    private Double radius;
    private static final double PI = 3.14;

    public Circle(String name, String color, Double radius) {
        this.name = name;
        this.color = color;
        this.radius = radius;
    }

    @Override
    Double square() {
        return radius * radius * PI;
    }
}
