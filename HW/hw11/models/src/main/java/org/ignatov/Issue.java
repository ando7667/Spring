package org.ignatov;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Запись о факте выдачи книги (в БД)
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "issues")
public class Issue {


  @Setter
  @Getter
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

  @Column(name = "timeissue")
  @Schema(name = "Дата и время выдачи")
  private LocalDateTime timeIssue;

  public Issue(long bookId, long readerId) {
    this.bookId = bookId;
    this.readerId = readerId;
    this.timeIssue = LocalDateTime.now();
  }

  public Issue fromIssue(IssueDataTemplate issueDTemplate) {
    Issue issuance = new Issue();
    issuance.setId(issueDTemplate.getId());
    issuance.setBookId(issueDTemplate.getBook().getId());
    issuance.setReaderId(issueDTemplate.getReader().getId());
    issuance.setTimeIssue(issueDTemplate.getTimeIssue());
    return issuance;
  }

}
