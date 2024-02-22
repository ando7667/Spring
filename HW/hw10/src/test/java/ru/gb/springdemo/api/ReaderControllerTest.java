package ru.gb.springdemo.api;

import ru.gb.springdemo.repository.ReaderRepository;
import ru.gb.springdemo.model.Reader;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebClient
public class ReaderControllerTest {
    @Autowired
    WebTestClient webTestClient;
    @Autowired
    ReaderRepository readerRepository;

    @Data
    @AllArgsConstructor
    static class JUnitReaderResponce {
        private Long id;
        private String name;
    }

    @Test
    void testFindByIdNotFound() {
        webTestClient.get()
                .uri("/book/-1")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testSave() {
        Reader request = new Reader("Тестовый читатель");


        JUnitReaderResponce responseBody = webTestClient.post()
                .uri("/reader")
                .bodyValue(request)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(JUnitReaderResponce.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody, "Responce Body is null");
        Assertions.assertNotNull(responseBody.getId(), "ID id null");
        Assertions.assertEquals(request.getId(), responseBody.getId(), "Id missmatch");
        Assertions.assertEquals(request.getName(), responseBody.getName(), "name missmatch");

        Assertions.assertTrue(readerRepository.findById(request.getId()).isPresent(), "reader not found in repository");
    }

    @Test
    void testFindByIdSuccess() {
        Reader expected = readerRepository.save(new Reader("тестовый Читатель"));

        ReaderControllerTest.JUnitReaderResponce responceBody = webTestClient.get()
                .uri("/reader/" + expected.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ReaderControllerTest.JUnitReaderResponce.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responceBody);
        Assertions.assertEquals(expected.getId(), responceBody.getId());
        Assertions.assertEquals(expected.getName(), responceBody.getName());
    }

    @Test
    void testGetAll() {
        readerRepository.saveAll(List.of(
                new Reader("Читатель-1"),
                new Reader("Читатель-2"),
                new Reader("Читатель-3")
        ));

        List<Reader> expected = readerRepository.findAll();

        List<ReaderControllerTest.JUnitReaderResponce> responceBody = webTestClient.get()
                .uri("/reader/all")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<ReaderControllerTest.JUnitReaderResponce>>() {
                })
                .returnResult()
                .getResponseBody();

        Assertions.assertEquals(expected.size(), responceBody.size());
        for (ReaderControllerTest.JUnitReaderResponce readerResponce : responceBody) {
            boolean found = expected.stream()
                    .filter(it -> Objects.equals(it.getId(), readerResponce.getId()))
                    .anyMatch(it -> Objects.equals(it.getName(), readerResponce.getName()));
            Assertions.assertTrue(found);
        }
    }


}