package ru.gb.springdemo.api;

import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.model.Book;


import java.util.List;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebClient
public class BookControllerTest {
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Data
    static class JUnitBook {
        private Long id;
        private String name;

    }

    @BeforeEach
    void clearBD(){
        bookRepository.deleteAll();
    }

    @Test
    void testFindById() {
        Book expected = bookRepository.save(new Book("testBook"));

        JUnitBook responceBody = webTestClient.get()
                .uri("/book/" + expected.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(JUnitBook.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responceBody);
        Assertions.assertEquals(expected.getId(), responceBody.getId());
        Assertions.assertEquals(expected.getName(), responceBody.getName());
    }


    @Test
    void testFindByIdNotFound() {
        webTestClient.get()
                .uri("/book/-1")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testGetAll() {
        List<Book> bookList = bookRepository.saveAll(List.of(
                new Book("book-1"),
                new Book("book-2"),
                new Book("book-3")
        ));


        List<JUnitBook> responceBody = webTestClient.get()
                .uri("/book/all")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<JUnitBook>>() {
                })
                .returnResult()
                .getResponseBody();

        Assertions.assertNotNull(responceBody);
        Assertions.assertEquals(bookList.size(), responceBody.size());
        for (JUnitBook bookResponce : responceBody) {
            boolean found = bookList.stream()
                    .filter(it -> Objects.equals(it.getId(), bookResponce.getId()))
                    .anyMatch(it -> Objects.equals(it.getName(), bookResponce.getName()));
            Assertions.assertTrue(found);
        }
    }

    @Test
    void testSave() {
        Book request = new Book(14L,"TestBook");

        JUnitBook responseBody = webTestClient.post()
                .uri("/book")
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(JUnitBook.class)
                .returnResult().getResponseBody();

        System.out.println(request);
        System.out.println(responseBody);

        Assertions.assertNotNull(responseBody);
        Assertions.assertNotNull(responseBody.getId());
        Assertions.assertEquals(request.getId(), responseBody.getId());
        Assertions.assertEquals(request.getName(), responseBody.getName());

        Assertions.assertTrue(bookRepository.findById(request.getId()).isPresent());
    }

}