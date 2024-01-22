package org.ignatov.hw2;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class StudentRepository {
    private static List<Student> students;

    public StudentRepository() {
        students = new ArrayList<>();
        students.add(new Student("Anton","group1"));
        students.add(new Student("Oleg","group2"));
        students.add(new Student("Alex","group3"));
        students.add(new Student("Elena","group1"));
        students.add(new Student("Sergey","group1"));
        students.add(new Student("Eduard","group1"));
        students.add(new Student("Olga","group2"));
        students.add(new Student("Ilya","group2"));
        students.add(new Student("Kiril","group3"));
        students.add(new Student("Mariya","group3"));
    }

    public List<Student> getAll() {
        return List.copyOf(students);
    }

    public Student getByName(String name) {
        return students.stream()
                .filter(it -> Objects.equals(it.getName(), name))
                .findFirst()
                .orElse(null);
    }

    public List<Student> getByNameAll(String name) {
        return students.stream()
                .filter(it -> Objects.equals(it.getGroupName(), name))
                .collect(Collectors.toList());
    }

    public Student getByGroup(String groupName) {
        return students.stream()
                .filter(it -> Objects.equals(it.getGroupName(), groupName))
                .findAny()
                .orElse(null);
    }

    public List<Student> getByGroupAll(String groupName) {
        return students.stream()
                .filter(it -> Objects.equals(it.getGroupName(), groupName))
                .collect(Collectors.toList());
    }

    public Student getById(long id) {
        return students.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }


    public void createStudent(Student student) {
        students.add(student);
    }

    public void deleteStudent(long id) {
        students.removeIf(student -> student.getId() == id);
    }

}
