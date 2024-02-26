package org.ignatov;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Книги",
                description = "Управление книгами",
                version = "0.0.1-SNAPSHOT"
        )
)
@SpringBootApplication
public class BookMicroService {
    public static void main(String[] args) {
        SpringApplication.run(BookMicroService.class, args);

    }
}