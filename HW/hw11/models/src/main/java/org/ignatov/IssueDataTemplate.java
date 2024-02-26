package org.ignatov;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueDataTemplate {
    private long id;
    private Book book;
    private Reader reader;
    private LocalDateTime timeIssue;
}
