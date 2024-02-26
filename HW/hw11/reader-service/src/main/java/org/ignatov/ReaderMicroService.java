package org.ignatov;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
    info = @Info(
            title = "Читатели",
            description = "Управление читателями",
            version = "0.0.1-SNAPSHOT"
    )
)
@SpringBootApplication
public class ReaderMicroService {
    public static void main(String[] args) {
        SpringApplication.run(ReaderMicroService.class, args);
    }
}