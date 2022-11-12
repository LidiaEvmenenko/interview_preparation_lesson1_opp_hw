package ru.geekbrains.hw1.task3;

public class Rectangle extends Figure {
    private Double side1;
    private Double side2;

    public Rectangle(String name, String color, Double side1, Double side2) {
        this.name = name;
        this.color = color;
        this.side1 = side1;
        this.side2 = side2;
    }

    @Override
    Double square() {
        return side1 * side2;
    }
}
