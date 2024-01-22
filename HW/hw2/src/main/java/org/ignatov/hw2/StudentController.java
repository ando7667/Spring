package org.ignatov.hw2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
//@RequestMapping("/student")
public class StudentController {

    private final StudentRepository repository;

    @Autowired
    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    // GET /student/{id} - получить студента по ID
    @GetMapping("/student/{id}")
    public Student getUser(@PathVariable long id) {
        return repository.getById(id);
    }

    // GET /student - получить всех студентов
    @GetMapping("/student")
    public List<Student> getStudents() {
        return repository.getAll();
    }

    // GET /student/search?name='studentName' - получить список студентов, чье имя содержит подстроку studentName
    @GetMapping("/student/search")
    public List<Student> getStudentByNameAll(@RequestParam String name) {
        return repository.getByNameAll(name);
    }

    // GET /group/{groupName}/student - получить всех студентов группы
    @GetMapping("/group/{groupName}/student")
    public List<Student> getStudentInGroup(@PathVariable String groupName) {
        return repository.getByGroupAll(groupName);
    }

    // POST /student - создать студента (принимает JSON) (отладиться можно с помощью Postman)
    @PostMapping("/student")
    public void createStudent(@RequestBody Student student) {
        repository.createStudent(student);
    }

    // DELETE /student/{id} - удалить студента
    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable long id) {
        repository.deleteStudent(id);
    }

    // PATCH /student/{id} - изменить студента (принимает JSON) (отладиться можно с помощью Postman)
    @PatchMapping("/student/{id}")
    public Student updateStudent(@PathVariable long id, @RequestBody Student student) {
        Student existsStudent = repository.getById(id);
        if (existsStudent == null) {
            throw new IllegalArgumentException();
        }

        existsStudent.setName(student.getName());
        return existsStudent;
    }

}
