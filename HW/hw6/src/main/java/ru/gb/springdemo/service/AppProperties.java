package ru.gb.springdemo.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("application")
public class AppProperties {
    private int maxAllowedBook;
}
