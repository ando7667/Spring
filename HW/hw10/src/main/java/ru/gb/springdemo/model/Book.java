package ru.gb.springdemo.model;

import jakarta.persistence.*;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Data
@Table(name = "book")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Schema(name = "Идентификатор книги")
  private long id;

  @Column(name = "name")
  @Schema(name = "Название книги")
  private String name;

  @Column(name = "author")
  @Schema(name = "Автор книги")
  private String author;

  @Column(name = "year")
  @Schema(name = "Год издания книги")
  private String year;

  public Book() {
  }
  public Book(String name, String author, String year) {
    this.name = name;
  }

}
