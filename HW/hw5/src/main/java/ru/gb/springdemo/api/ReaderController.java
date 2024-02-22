package ru.gb.springdemo.api;

import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.ReaderRepository;
import ru.gb.springdemo.service.ReaderService;

import java.util.List;

@RestController
@RequestMapping("/reader")
public class ReaderController {

    private final ReaderRepository readerRepository;
    private final ReaderService readerService;

    public ReaderController(ReaderRepository readerRepository, ReaderService readerService) {
        this.readerRepository = readerRepository;
        this.readerService = readerService;
    }

    @GetMapping("/all")
    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    // GET /reader/{id} - получить описание книги
    @GetMapping("/{id}")
    public Reader getReader(@PathVariable long id) {
        return readerRepository.findById(id).get();
    }

    // DELETE /reader/{id} - удалить книгу
    @DeleteMapping("/{id}")
    public void deleteReader(@PathVariable long id) {
        readerRepository.deleteById(id);
    }

    //  POST /reader - создать книгу
    @PostMapping
    public void createReader(@RequestBody Reader reader) {
        readerRepository.save(reader);

    }
}
