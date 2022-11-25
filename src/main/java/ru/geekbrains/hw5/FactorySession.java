package ru.geekbrains.hw5;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class FactorySession {
    public static SessionFactory forcePrepareData() {
        return new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

    }
}
