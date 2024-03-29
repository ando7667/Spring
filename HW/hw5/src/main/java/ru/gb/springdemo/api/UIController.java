package ru.gb.springdemo.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.IssueDataTemplate;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;
import ru.gb.springdemo.service.IssuerService;
import ru.gb.springdemo.service.ReaderService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UIController {

    private final ReaderRepository readerRepository;
    private final BookRepository bookRepository;
    private final IssueRepository issueRepository;
    private final ReaderService readerService;
    private final IssuerService issuerService;

    @GetMapping("/ui/books")
    public String getAllBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "booksTable";
    }

    @GetMapping("/ui/readers")
    public String getAllReaders(Model model) {
        List<Reader> readers = readerRepository.findAll();
        model.addAttribute("readers", readers);
        return "readersTable";
    }

    @GetMapping("/ui/issues")
    public String getAllIssues(Model model) {
        List<Issue> issues = issueRepository.findAll();
        List<IssueDataTemplate> issueDataTemplates = new ArrayList<>();
        for(Issue issue: issues) {
            String bookName = issuerService.getBookById(issue.getBookId()).getName();
            String readerName = issuerService.getReaderById(issue.getReaderId()).getName();
            LocalDateTime timeIssue = issuerService.getIssueById(issue.getId()).getTimeIssue();
            IssueDataTemplate issueDataTemplate = new IssueDataTemplate(issue.getId(),bookName, readerName, timeIssue);
            issueDataTemplates.add(issueDataTemplate);
        }
        model.addAttribute("issues", issueDataTemplates);
        return "issuesTable";
    }

}
