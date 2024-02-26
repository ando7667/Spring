package org.ignatov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ignatov.IssueDataTemplate;
import org.ignatov.aspect.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ignatov.Issue;
import org.ignatov.repository.IssueRepository;
import org.ignatov.service.IssuerService;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/issue")
@Tag(name = "Записи о выдаче книг", description = "Управление выдачей книг")
@Time
public class IssuerController {

  @Autowired
  private IssuerService issuerService;
  private IssueRepository issueRepository;


  // получить список всех выдач
  @Operation(
          summary = "Получение списка выдач",
          description = "Получить список всех выдач книг читателям",
          responses = {
                  @ApiResponse(responseCode = "200", description = "Получили список выдач", content = {
                          @Content(mediaType = "application/json", array = @ArraySchema(schema =
                          @Schema(implementation = Issue.class)
                          ))
                  }),
                  @ApiResponse(responseCode = "404", description = "Книги не выдавались", content = {
                          @Content(mediaType = "*/*", schema = @Schema(implementation = String.class))
                  }),
                  @ApiResponse(responseCode = "503", description = "Нет связи с сервисом",
                          content = {@Content(mediaType = "*/*", schema = @Schema(implementation = String.class))})
          }
  )
  @GetMapping
  public ResponseEntity<List<IssueDataTemplate>> issueList() {
    final List<IssueDataTemplate> issueList;
    try {
      issueList = issuerService.getAllIssues();
    } catch (NullPointerException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage());
    }
    return ResponseEntity.status(HttpStatus.OK).body(issueList);
  }


  // добавить выдачу
  @Operation(
          summary = "Добавить выдачу",
          description = "Добавить выдачу книги читателю",
          responses = {
                  @ApiResponse(responseCode = "201", description = "Выдача сохранена", content = {
                          @Content(mediaType = "application/json", schema =
                          @Schema(implementation = Issue.class)
                          )
                  }),
                  @ApiResponse(responseCode = "404", description = "Введенные данные не найдены", content =
                          {@Content(mediaType = "*/*", schema = @Schema(implementation = String.class))
                          }),
                  @ApiResponse(responseCode = "405", description = "Сохранение данных не выполнено", content = {
                          @Content(mediaType = "*/*", schema = @Schema(implementation = String.class))
                  }),
                  @ApiResponse(responseCode = "503", description = "Соединение с сервисом не установлено",
                          content = {@Content(mediaType = "*/*", schema = @Schema(implementation = String.class))})
          }
  )
  // POST /issue {"readerId": 25, "bookId": 57}
  @PostMapping
  public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
    log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

    final Issue issue;
    try {
      issue = issuerService.newIssue(request);

    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    } catch (IllegalStateException e) {
      throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
    } catch (RuntimeException e) {
      throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage());
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(issue);
  }

  // получение выдачи по ее ид
  @Operation(
          summary = "Получить выдачу",
          description = "Получить выдачу книги читателю по ид выдачи",
          parameters = {
                  @Parameter(name = "id", description = "ид выдачи")
          },
          responses = {
                  @ApiResponse(responseCode = "200", description = "Выдача книги по " + "ид",
                          content = {
                          @Content(mediaType = "application/json", schema = @Schema(implementation = Issue.class))
                  }),
                  @ApiResponse(responseCode = "404", description = "Выдача не найдена", content = {
                          @Content(schema = @Schema(implementation = String.class))
                  })
          }
  )
  @GetMapping("/{id}")
  public ResponseEntity<IssueDataTemplate> getIssueById(@PathVariable long id) {
    IssueDataTemplate issueDataTemplate;
    try {
      issueDataTemplate = issuerService.getIssueByIdDT(id);
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
    return ResponseEntity.status(HttpStatus.OK).body(issueDataTemplate);
  }


  // получения списка книг выданных читателю
  @Operation(
          summary = "Получить список выданных книг читателю",
          description = "Получение списка книг по ид читателя",
          parameters = {
                  @Parameter(name = "id", description = "ид читателя")
          },
          responses = {
                  @ApiResponse(responseCode = "200", description = "Список  выданных книг читателю получен",
                          content = {@Content(mediaType = "application/json", schema =
                          @Schema(implementation = Issue.class))}),
                  @ApiResponse(responseCode = "404", description = "Книги читателю не выдавались",
                          content = {
                                  @Content(mediaType = "*/*", schema = @Schema(implementation = String.class))
                          })
          }
  )
  @GetMapping("/reader/{id}")
  public ResponseEntity<List<IssueDataTemplate>> getIssueByReaderId(@PathVariable Long id) {
    List<IssueDataTemplate> issueList;
    try {
      issueList = issuerService.getIssueByRaderIdDT(id);
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
    return ResponseEntity.status(HttpStatus.OK).body(issueList);
  }

}
