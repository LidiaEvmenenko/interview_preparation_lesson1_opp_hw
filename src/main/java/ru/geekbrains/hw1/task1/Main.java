package ru.geekbrains.hw1.task1;

public class Main {
    public static void main(String[] args) {
        Person p = Person.createBuilder()
                .withFirstName("Иванов")
                .withLastName("Иван")
                .withMiddleName("Иванович")
                .withCountry("Россия")
                .withAddress("г.Москва")
                .withPhone("9123456789")
                .withAge(45)
                .withGender("m")
                .build();
        System.out.println(p.toString());
    }
}
