package org.ignatov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.ignatov.Reader;
import org.ignatov.aspect.Time;
import org.ignatov.services.ReaderIssueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import org.ignatov.services.ReaderService;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reader")
@Tag(name = "Читатель")
@Time
public class ReaderController {

    private final ReaderService readerService;
    private final ReaderIssueService readerIssueService;

    @Operation(
            summary = "Список всех читателей",
            description = "Получить всех читателей",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Список читателей получен",
                            content = {
                                @Content(mediaType = "application/json",
                                        array = @ArraySchema(schema = @Schema(implementation = Reader.class)))
                    })
            }
    )
    // GET /reader - получить список читателей
    @GetMapping()
    public ResponseEntity<List<Reader>> getAllReaders() {
        final List<Reader> readers;
        try {
            readers = readerService.getReaderList();

        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(readers);
    }

    @Operation(
            summary = "Получить читателя",
            description = "Получить читателя по ид",
            parameters = {
                    @Parameter(name = "id", description = "ид читателя")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Читатель получен", content = {
                            @Content(mediaType = "*/*", schema = @Schema(implementation = String.class))
                    })
            }
    )
    // GET /reader/{id} - получить читателя по ид
    @GetMapping("/{id}")
    public ResponseEntity<Reader> getReader(@PathVariable long id) {
        final Reader reader;
        try {
            reader = readerService.getReaderById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(reader);
    }

    @Operation(
            summary = "Удаление читателя",
            description = "Удаление читателя по ид",
            parameters = {
                    @Parameter(name = "id", description = "Ид читателя")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Читатель удален", content = {
                            @Content(mediaType = "application/json", schema =
                            @Schema(implementation = Reader.class)
                            )
                    }),
                    @ApiResponse(responseCode = "404", description = "Читатель с таким ид не найден", content = {
                            @Content(mediaType = "*/*", schema = @Schema(implementation = String.class))
                    })
            }
    )
    // DELETE /reader/{id} - удалить читателя по ид
    @DeleteMapping("/{id}")
    public ResponseEntity<Reader> deleteReader(@PathVariable long id) {
        final Reader reader;
        try {
            reader = readerService.deleteReaderById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(reader);
    }

    @Operation(
            summary = "Добавить читателя",
            description = "Добовление читателя в базу",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Читатель добавлен в базу", content = {
                            @Content(mediaType = "application/json", schema =
                                @Schema(implementation = Reader.class))
                    }),
                    @ApiResponse(responseCode = "405", description = "Не удалось добавить читателя в базу", content = {
                            @Content(mediaType = "*/*", schema = @Schema(implementation = String.class))
                    })
            }
    )
    //  POST /reader - добавить читателя
    @PostMapping
    public ResponseEntity<Reader> createReader(@RequestBody Reader reader) {

        try {
            reader = readerService.addNewReader(reader);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(reader);
    }
}
