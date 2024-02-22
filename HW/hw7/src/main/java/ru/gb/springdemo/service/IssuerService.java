package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.gb.springdemo.api.IssueRequest;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class IssuerService {

  // спринг это все заинжектит
  private final BookRepository bookRepository;
  private final ReaderRepository readerRepository;
  private final IssueRepository issueRepository;

  public Issue issue(IssueRequest request) {
    if (bookRepository.findById(request.getBookId()).get() == null) {
      throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
    }
    if (readerRepository.findById(request.getReaderId()).get() == null) {
      throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
    }
    if (issueRepository.findById(request.getReaderId()).get() != null) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "У читателя уже есть книги на руках");
    }

    // можно проверить, что у читателя нет книг на руках (или его лимит не превышает в Х книг)

    Issue issue = new Issue(request.getBookId(), request.getReaderId());
    issueRepository.save(issue);
    return issue;
  }

  public Book getBookById(Long id) {  return bookRepository.findById(id).get();   }

  public Reader getReaderById(Long id) {
    return readerRepository.findById(id).get();
  }

  public Issue getIssueById(Long id) {
    return issueRepository.findById(id).get();
  }

  public List<Issue> getAllIssues() { return  issueRepository.findAll(); }
}
