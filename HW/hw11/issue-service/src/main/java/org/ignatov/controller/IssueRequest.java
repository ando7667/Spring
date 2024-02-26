package org.ignatov.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Сущность запроса на выдачу")
public class IssueRequest {

  @Schema(description = "ид читателя")
  private long readerId;

  @Schema(description = "ид книги")
  private long bookId;

}
