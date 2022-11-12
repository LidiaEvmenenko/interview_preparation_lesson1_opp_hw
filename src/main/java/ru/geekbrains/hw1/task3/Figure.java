package ru.geekbrains.hw1.task3;

abstract class Figure {
    protected String name;
    protected String color;

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    abstract Double square();
}
