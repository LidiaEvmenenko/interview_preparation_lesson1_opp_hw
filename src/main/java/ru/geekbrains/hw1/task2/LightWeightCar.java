package ru.geekbrains.hw1.task2;

public class LightWeightCar extends Car implements Moveable,Stopable {

    public LightWeightCar(IEngine engine, String color, String name) {
        this.engine = engine;
        this.color = color;
        this.name = name;

    }
    @Override
    public void open() {
        System.out.println("Car LightWeight is opening");
    }
    @Override
    public void move() {
        System.out.println("Car LightWeight is moving");
    }
    @Override
    public void start() {
        System.out.println("Car LightWeight starting");
    }

    @Override
    public void stop() {
        System.out.println("Car LightWeight stoping");
    }
}
