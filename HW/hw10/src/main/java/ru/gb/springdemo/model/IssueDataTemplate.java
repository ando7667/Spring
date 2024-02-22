package ru.gb.springdemo.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IssueDataTemplate {
    private final long id;
    private final String bookName;
    private final String readerName;
    private final LocalDateTime timeIssue;
}
