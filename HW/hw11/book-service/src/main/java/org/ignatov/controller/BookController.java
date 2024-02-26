package org.ignatov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.ignatov.aspect.Time;
import org.ignatov.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.bind.annotation.*;
import org.ignatov.Book;
import org.ignatov.repository.BookRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
@Tag(name= "Книги", description = "Управление книгами")
@Time
public class BookController {

    private final BookService bookService;

    @Operation(
            summary = "Получить список книг",
            description = "Получить все книги из базы",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список книг получен", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema =
                            @Schema(implementation = Book.class)
                            ))
                    }),
                    @ApiResponse(responseCode = "404", description = "В базе нет книг",
                            content = {
                                    @Content(mediaType = "*/*", schema = @Schema(implementation = String.class))
                            })
            }
    )

    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks() {

        final List<Book> books;
        try {
            books = bookService.getBooksList();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @Operation(
            summary = "Получить книгу",
            description = "получаем книгу по её ид",
            parameters = {
                    @Parameter(name = "id", description = "ид книги")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Книга получена", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)
                            )
                    }),
                    @ApiResponse(responseCode = "404", description = "Книга не найдена.",
                            content = {
                                    @Content(mediaType = "*/*", schema = @Schema(implementation = String.class))
                            })
            }
    )
    // GET /book/{id} - получить книгу по ее ид
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable long id) {
        final Book book;
        try {
            book = bookService.getBookById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @Operation(
            summary = "Удалить книгу",
            description = "Удалить книгу по её ид",
            parameters = {
                    @Parameter(name = "id", description = "ид книги")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Книга удалена", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)
                            )
                    }),
                    @ApiResponse(responseCode = "404", description = "Книга с таким ид нет. Удаление не возможно",
                            content = {
                                    @Content(mediaType = "*/*", schema = @Schema(implementation = String.class))
                            })
            }
    )
    // DELETE /book/{id} - удалить книгу
    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable long id) {
        final Book book;
        try {
            book = bookService.deleteBookById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @Operation(
            summary = "Добавить книгу",
            description = "Добавляем книгу в базу",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Книга добавлена", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)
                            )
                    }),
                    @ApiResponse(responseCode = "405", description = "Книгу добавить не удалось",
                            content = {
                                    @Content(mediaType = "*/*", schema = @Schema(implementation = String.class))
                            })
            }
    )
    //  POST /book - создать книгу
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        try {
            book = bookService.addNewBook(book);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

}