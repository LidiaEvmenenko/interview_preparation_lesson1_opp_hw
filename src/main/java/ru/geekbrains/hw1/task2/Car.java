package ru.geekbrains.hw1.task2;

abstract class Car {
    protected IEngine engine;
    protected String color;
    protected String name;


    abstract void open();

    public IEngine getEngine() {
        return engine;
    }
    public void setEngine(IEngine engine) {
        this.engine = engine;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return String.format("Car{engine=%s, color='%s', name='%s'}", engine.startEngine(), color, name);
    }
}