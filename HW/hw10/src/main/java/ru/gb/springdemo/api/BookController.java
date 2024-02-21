package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.BookRepository;

import java.util.List;

@RestController
@RequestMapping("/book")
@Tag(name= "Книги")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @GetMapping("/all")
    @ManagedOperation
    @Operation(summary = "Список всех книг", description = "Получаем список всех книг")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // GET /book/{id} - получить описание книги
    @GetMapping("/{id}")
    @Operation(summary = "Получить книгу по её ид", description = "получаем книгу по её ид")
    public Book getBook(@PathVariable long id) {
        return bookRepository.findById(id).get();
    }

    // DELETE /book/{id} - удалить книгу
    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить книгу по её ид", description = "удвляем книгу по её ид")
    public void deleteBook(@PathVariable long id) {
        bookRepository.deleteById(id);
    }

    //  POST /book - создать книгу
    @PostMapping
    @Operation(summary = "Создание книги", description = "Добавляем книгу в библиотеку")
    public void createBook(@RequestBody Book book) {
        bookRepository.save(book);

    }
}