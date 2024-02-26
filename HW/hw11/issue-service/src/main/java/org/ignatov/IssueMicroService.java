package org.ignatov;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Выдачи книг",
                description = "Выдача книг читателям",
                version = "0.0.1-SNAPSHOT"
        )
)
public class IssueMicroService {
    public static void main(String[] args) {
        SpringApplication.run(IssueMicroService.class, args);
    }
}