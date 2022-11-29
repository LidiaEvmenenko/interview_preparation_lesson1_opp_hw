package ru.geekbrains.hw7;

import lombok.Data;

@Data
public class StudentDto {
    private Long id;
    private String name;
    private int age;

    public StudentDto() {
    }

    public StudentDto(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public StudentDto(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.age = student.getAge();
    }
}
