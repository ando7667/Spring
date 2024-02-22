package ru.gb.springdemo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Запись о факте выдачи книги (в БД)
 */
@Data
@Entity
@Table(name = "issues")
public class Issue {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "bookId")
  private long bookId;

  @Column(name = "readerId")
  private long readerId;

  /**
   * Дата выдачи
   */
  @Column(name = "timeissue")
  private LocalDateTime timeIssue;

  public Issue() {
  }

  public Issue(long bookId, long readerId) {
    this.bookId = bookId;
    this.readerId = readerId;
    this.timeIssue = LocalDateTime.now();
  }

}
