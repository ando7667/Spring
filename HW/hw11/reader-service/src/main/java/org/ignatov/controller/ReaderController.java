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
    // GET /reader/{id} - получить описание книги
    @GetMapping("/{id}")
    public Reader getReader(@PathVariable long id) {
        return readerRepository.findById(id).get();
    }

    // DELETE /reader/{id} - удалить книгу
    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить читателя", description = "Удаление читателя по его ид")
    public void deleteReader(@PathVariable long id) {
        readerRepository.deleteById(id);
    }

    //  POST /reader - создать книгу
    @PostMapping
    @Operation(summary = "Создать читателя", description = "Создаем читателя")
    public void createReader(@RequestBody Reader reader) {
        readerRepository.save(reader);

    }
}
