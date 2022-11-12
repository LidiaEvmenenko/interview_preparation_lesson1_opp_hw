package ru.geekbrains.hw1.task3;

public class Triangle extends Figure{
    private Double side1;
    private Double side2;
    private Double base;
    private Double height;

    public Triangle(String name, String color, Double side1, Double side2, Double side3, Double height) {
        this.name = name;
        this.color = color;
        this.side1 = side1;
        this.side2 = side2;
        this.base = side3;
        this.height = height;
    }

    @Override
    public Double square() {
        return base * height / 2;
    }
}
