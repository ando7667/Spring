package ru.gb.springdemo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "book")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "author")
  private String author;

  @Column(name = "year")
  private String year;

  public Book() {
  }
  public Book(String name, String author, String year) {
    this.name = name;
  }

}
