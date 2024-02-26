package org.ignatov.service;

import org.ignatov.*;
import org.ignatov.controller.IssueRequest;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import org.ignatov.repository.IssueRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@EnableConfigurationProperties(IssueProperty.class)
public class IssuerService {
  private final IssueRepository issueRepository;

  private final IssueProperty maxAllowedBooks;
  private final WebClient webClient;

  public IssuerService(IssueRepository issueRepository, IssueProperty maxAllowedBooks,
                       ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction) {
                          this.issueRepository = issueRepository;
                          this.maxAllowedBooks = maxAllowedBooks;
                          this.webClient = WebClient.builder()
                              .filter(loadBalancerExchangeFilterFunction)
                              .build();
  }


  // добавить запись выдачи в базу
  public Issue newIssue(IssueRequest request) {
    if (getBookByIdService(request.getBookId()) == null) {
      throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
    }
    if (getReaderByIdService(request.getReaderId()) == null) {
      throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
    }
    if ((long) issueRepository.findIssueByReaderId(request.getReaderId()).size() >= maxAllowedBooks.getMaxAllowedBook() ) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "У читателя максимальное кол-во книг на руках");
    }

    Issue issue = new Issue(request.getBookId(), request.getReaderId());
    issueRepository.save(issue);
    return issue;
  }


// получить список всех выдач
  public List<IssueDataTemplate> getAllIssues() {
    List<Issue> issues =  issueRepository.findAll();
    if (issues.isEmpty()) {
      throw new NullPointerException("Книги читателям не выдавались");
    }
    return getIssueDT(issues);
  }

  // преобразование списка Issue в IssueDataTemplate
  private List<IssueDataTemplate> getIssueDT(List<Issue> issues) {
    List<IssueDataTemplate> issueDataTemplates = new ArrayList<>();
    try {
      for (Issue issue : issues) {
        issueDataTemplates.add(newIssueDataTemplate(issue));
      }
    } catch (Exception e) {
      throw new RuntimeException("нет связи с сервисом");
    }
    return issueDataTemplates;
  }

  // поиск выдачи по ид
  public IssueDataTemplate getIssueByIdDT(Long id) {
    Issue issue = issueRepository.findById(id).orElseThrow(
            () -> new NoSuchElementException("Выдачи с ид:" + id + "не существует")
        );
    IssueDataTemplate issueDataTemplate;
    try {
      issueDataTemplate = newIssueDataTemplate(issue);
    } catch (Exception e) {
      throw new RuntimeException("нет связи с сервисом");
    }
    return issueDataTemplate;
  }

  // поиск выдач книг читателю с данным ид
  public List<IssueDataTemplate> getIssueByRaderIdDT(Long id) {
      List<Issue> issues = issueRepository.findIssueByReaderId(id);
      if (issues.isEmpty()) {
        throw new NoSuchElementException("Читателю с id:" + id + " книги не выдавались");
      }
      return getIssueDT(issues);
  }

  // преобразование Issue в IssueDataTemplate
  public IssueDataTemplate newIssueDataTemplate(Issue issue) throws WebClientResponseException {
    IssueDataTemplate issueDataTemplate = new IssueDataTemplate();
    issueDataTemplate.setId(issue.getId());
    issueDataTemplate.setBook(getBookByIdService(issue.getBookId()));
    issueDataTemplate.setReader(getReaderByIdService(issue.getReaderId()));
    issueDataTemplate.setTimeIssue(issue.getTimeIssue());
    return issueDataTemplate;
  }

  /**
   * Метод получает книгу по ее ид через сервис book-service
   * @param id - ид книги
   * @return  книгу
   */
  private Book getBookByIdService(Long id) {
    return webClient.get()
            .uri("http://BOOK-SERVICE/book/" + id)
            .retrieve()
            .bodyToMono(Book.class)
            .block();
  }

  /**
   * Метод получает читателя по его ид через сервис reader-service
   * @param id - ид читателя
   * @return  читателя
   */
  private Reader getReaderByIdService(Long id) {
    return webClient.get()
            .uri("http://READER-SERVICE/reader/" + id)
            .retrieve()
            .bodyToMono(Reader.class)
            .block();
  }

}

