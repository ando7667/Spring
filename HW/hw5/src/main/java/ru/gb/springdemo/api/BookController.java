package ru.gb.springdemo.api;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.BookRepository;

import java.util.List;

@RestController
@RequestMapping("/book")
//@Tag(name= "Книги")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @GetMapping("/all")
    @ManagedOperation
    //@Operation(summary = "Список всеч книг", description = "Получить список всех книг")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // GET /book/{id} - получить описание книги
    @GetMapping("/{id}")

    public Book getBook(@PathVariable long id) {
        return bookRepository.findById(id).get();
    }

    // DELETE /book/{id} - удалить книгу
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id) {
        bookRepository.deleteById(id);
    }

    //  POST /book - создать книгу
    @PostMapping
    public void createBook(@RequestBody Book book) {
        bookRepository.save(book);

    }
}