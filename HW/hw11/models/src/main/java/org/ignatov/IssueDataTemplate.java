package org.ignatov;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class IssueDataTemplate {
    private final long id;
    private final Book book;
    private final Reader reader;
    private final LocalDateTime timeIssue;
}
