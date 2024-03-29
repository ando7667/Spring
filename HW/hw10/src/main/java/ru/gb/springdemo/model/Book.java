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

  public Book() {
  }

  public Book(String name) {
    this.name = name;
  }

  public Book(Long id, String name) {
    this.id = id;
    this.name = name;

  }
}