package org.ignatov.seminar2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController
@RequestMapping("/users")

public class UserController {

    // HTTP - HyperText Transfer Protocol
    // GET POST PUT PATCH DELETE ... HEAD OPTIONS

    // REST - соглашение
    // GET /users/{id} => 404 (Not Found)
    // GET /users?nameLike='Igor%' => (No Content)

    // DELETE /users/{id}   - удаляет
    // POST /users/  {"id":"1", "name":"newName"} - создает
    // PUT  /users/{id}  {"id":"1", "name":"newName"} - изменяет


    // http://ip-address:port/users/all   -> список всех пользователей
    // http://ip-address:port/users/2     -> пользователя с ид = 2
    // http://ip-address:port/users?name=Alex   -> пользователя с именем Alex

//@Autowired инъекция через поле
    private final UserRepository repository;


// инъекция через сеттер
//    @Autowired
//    public void setRepository(UserRepository repository) {
//        this.repository = repository;
//    }

// инъекция через конструктор
    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    // */users/all
    //@RequestMapping(path = "/users", method = RequestMethod.GET)
    @GetMapping("/all")
    public List<User> getUsers() {
        return repository.getAll();
    }

    // */users/1
    @GetMapping("/{id}")
    public User getUser(@PathVariable long id) {
        return repository.getById(id);
    }

    // */users?name=Ivan
    @GetMapping
    public User getUserByName(@RequestParam(required = false) String name) {
        return repository.getByName(name);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable long id, @RequestBody User user) {
        User existUser = repository.getById(id);
        if ((existUser == null)) {
            throw new IllegalArgumentException();
        }
        existUser.setName(user.getName());
        return existUser;
    }

    @GetMapping(value = "/test", produces = MediaType.TEXT_HTML_VALUE)
    public String test() {
        return """
                <h1>Title</h1>
                """;
    }

}
