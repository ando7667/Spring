package ru.gb.springdemo.api;

import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.BookRepository;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    // GET /book/{id} - получить описание книги
    @GetMapping("/{id}")
    public Book getBook(@PathVariable long id) {
        return bookRepository.getBookById(id);
    }

    // DELETE /book/{id} - удалить книгу
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id) {
        bookRepository.deleteBook(id);
    }

    //  POST /book - создать книгу
    @PostMapping
    public void createBook(@RequestBody Book book) {
        bookRepository.createBook(book);

    }
}