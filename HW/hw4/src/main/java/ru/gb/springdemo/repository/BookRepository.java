package ru.gb.springdemo.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class BookRepository {

  private final List<Book> books;

  public BookRepository() {
    this.books = new ArrayList<>();
  }

  @PostConstruct
  public void generateData() {
    books.addAll(List.of(
      new Book("война и мир"),
      new Book("метрвые души"),
      new Book("чистый код"),
      new Book("экспансия"),
      new Book("экспансия"),
      new Book("php и mysql")
    ));
  }

  public Book getBookById(long id) {
    return books.stream().filter(it -> Objects.equals(it.getId(), id))
      .findFirst()
      .orElse(null);
  }

  public void createBook(Book book) {
    books.add(book);
  }


  public void deleteBook(long id) {
    books.removeIf(book -> book.getId() == id);
  }

  public List<Book> getAllBooks() {
    return List.copyOf(books);
  }

}
