package ru.geekbrains.hw1.task2;

public class Main {
    public static void main(String[] args) {

        IEngine engine = new Engine();
        LightWeightCar car = new LightWeightCar(engine, "red", "carLightWeight");
        System.out.println(car);
        car.start();
        car.move();
        car.stop();
        car.open();
        engine = new NewEngine();
        Lorry car1 = new Lorry(engine, "black", "carLorry");
        System.out.println(car);
        car1.start();
        car1.move();
        car1.stop();
        car1.open();
    }
}
