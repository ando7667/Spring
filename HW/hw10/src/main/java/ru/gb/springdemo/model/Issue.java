package ru.gb.springdemo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Запись о факте выдачи книги (в БД)
 */
@Data
@Entity
@Table(name = "issues")
public class Issue {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Schema(name = "Идентификатор выдачи")
  private long id;

  @Column(name = "bookId")
  @Schema(name = "Идентификатор книги")
  private long bookId;

  @Column(name = "readerId")
  @Schema(name = "Идентификатор читателя")
  private long readerId;

  /**
   * Дата выдачи
   */
  @Column(name = "timeissue")
  @Schema(name = "Дата и время выдачи")
  private LocalDateTime timeIssue;

  public Issue() {
  }

  public Issue(long bookId, long readerId) {
    this.bookId = bookId;
    this.readerId = readerId;
    this.timeIssue = LocalDateTime.now();
  }

}
