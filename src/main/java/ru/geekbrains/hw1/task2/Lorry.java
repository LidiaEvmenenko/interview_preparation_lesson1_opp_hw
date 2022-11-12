package ru.geekbrains.hw1.task2;

public class Lorry extends Car  implements Moveable,Stopable {

    public Lorry(IEngine engine, String color, String name) {
        this.engine = engine;
        this.color = color;
        this.name = name;
    }
    @Override
    public void move(){
        System.out.println("Car Lorry is moving");
    }
    @Override
    public void stop(){
        System.out.println("Car Lorry is stop");
    }
    @Override
    public void start() {
        System.out.println("Car Lorry is starting");
    }
    @Override
    public void open() {
        System.out.println("Car Lorry is open");
    }
}
