package org.ignatov.service;
import lombok.RequiredArgsConstructor;
import org.ignatov.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.ignatov.Book;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book getBookById(long id) {
        return bookRepository.findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("Книга с id:" + id + " не найдена")
                );
    }


    public List<Book> getBooksList() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            throw new NoSuchElementException("В базе нет книг");
        }
        return books;
    }

    public Book addNewBook(Book book) {
        if (book.getName().isEmpty()) {
            throw new RuntimeException("Название книги не задано");
        }
        return bookRepository.save(book);
    }


    public Book deleteBookById(long id) {
        Book book = getBookById(id);
        bookRepository.deleteById(id);
        if (Objects.isNull(book)) {
            throw new NoSuchElementException("Книга с id:" + id + " не найдена");
        }
        return book;
    }
}