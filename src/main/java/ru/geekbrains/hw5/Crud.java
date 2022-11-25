package ru.geekbrains.hw5;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Crud {
    private static SessionFactory factory;

    public static void init() {
        factory = FactorySession.forcePrepareData();
    }

    public static void createTableStudent() throws IOException {
        String sql = Files.lines(Paths.get("create.sql")).collect(Collectors.joining(" "));
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.createNativeQuery(sql).executeUpdate();
        session.getTransaction().commit();
    }

    public static void insertAllStudent() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        for (int i = 0; i < 10; i++) {
            String s = "student" + (i + 1);
            Student student = new Student(1L, s, 1);
            int mark = (int) (Math.random()*10);
            while (mark<3 || mark>5){
                mark = (int) (Math.random()*10);
            }
            student.setMark(mark);
            session.save(student);
        }
        session.getTransaction().commit();
    }

    public static void selectAllStudent() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        List<Student> list = session.createQuery("from Student").getResultList();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        session.getTransaction().commit();
    }
    public static void selectStudentById(long id){
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Student student = session.createQuery("select s from Student s where s.id = "+id, Student.class).getSingleResult();
        System.out.println(student);
        session.getTransaction().commit();
    }

    public static void insertStudent(String name, int mark) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Student student = new Student(1L, name, mark);
        session.save(student);
        session.getTransaction().commit();
    }

    public static void updateStudent(long id, int mark) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Student student = session.get(Student.class, id);
        student.setMark(mark);
        session.getTransaction().commit();
    }

    public static void deleteExample(long id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Student student = session.get(Student.class, (long)id);
        session.delete(student);
        session.getTransaction().commit();
    }

    public static void shutdown() {
        factory.close();
    }
}
