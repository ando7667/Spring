package org.ignatov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.ignatov.Reader;
import org.ignatov.aspect.Time;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import org.ignatov.repository.ReaderRepository;
import org.ignatov.services.ReaderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reader")
@Tag(name = "Читатель")
@Time
public class ReaderController {

    private final ReaderRepository readerRepository;
    private final ReaderService readerService;

    public ReaderController(ReaderRepository readerRepository, ReaderService readerService) {
        this.readerRepository = readerRepository;
        this.readerService = readerService;
    }

    @GetMapping("/all")
    @Operation(summary = "Получить список всех читателей", description = "Получаем списк всех читателей")
    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    // GET /reader/{id} - получить описание книги
    @GetMapping("/{id}")
    @Operation(summary = "Получить читателя", description = "Получаем читателя по его ид")
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
