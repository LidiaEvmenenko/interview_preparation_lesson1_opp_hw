package ru.geekbrains.hw5;

public class Main {
    public static void main(String[] args) {
        try {
            Crud.init();
            Crud.createTableStudent();
            Crud.insertAllStudent();
            Crud.selectAllStudent();
            Crud.updateStudent(1, 2);
            Crud.updateStudent(2, 2);
            Crud.updateStudent(3, 2);
            Crud.updateStudent(4, 2);
            Crud.deleteExample(1);
            Crud.deleteExample(7);
            Crud.deleteExample(9);
            Crud.insertStudent("STUDENT1", 4);
            Crud.insertStudent("STUDENT2", 5);
            Crud.selectAllStudent();
            Crud.selectStudentById(11);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Crud.shutdown();
        }
    }
}
