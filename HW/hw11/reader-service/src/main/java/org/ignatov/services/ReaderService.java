package org.ignatov.services;

import lombok.RequiredArgsConstructor;
import org.ignatov.Reader;
import org.ignatov.repository.ReaderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;

    public Reader getReaderById(long id) {
        return readerRepository
                .findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("Читатель с id:" + id + " не найден")
                );
    }

    public List<Reader> getReaderList() {
        List<Reader> readers = readerRepository.findAll();
        if (readers.isEmpty()) {
            throw new NoSuchElementException("Список читателей пуст");
        }
        return readers;
    }


    public Reader addNewReader(Reader reader) {
        if (reader.getName().isEmpty()) {
            throw new RuntimeException("Имя читателя не задано");
        }
        return readerRepository.save(reader);
    }


    public Reader deleteReaderById(long id) {
        Reader reader = getReaderById(id);

        if (Objects.isNull(reader)) {
            throw new NoSuchElementException("Читатель с ID = " + id + " не найден");
        }else {
            readerRepository.deleteById(id);
        }
        return reader;
    }

}