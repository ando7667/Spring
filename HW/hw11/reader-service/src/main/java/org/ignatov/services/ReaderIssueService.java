package org.ignatov.services;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.RequiredArgsConstructor;
import org.ignatov.Issue;
import org.ignatov.IssueDataTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class ReaderIssuesService {
    private final WebClient webClient = WebClient.builder().build();
    private final EurekaClient eurekaClient;
    private final ReaderBookService readerBookService;
    private final ReaderService readerService;


    private String getIssuanceServiceIp() {
        Application application = eurekaClient.getApplication("ISSUANCE-SERVICE");
        List<InstanceInfo> instanceInfoList = application.getInstances();
        int indexInstance = ThreadLocalRandom.current().nextInt(instanceInfoList.size());
        InstanceInfo randomInstanceInfo = instanceInfoList.get(indexInstance);
        return randomInstanceInfo.getHomePageUrl();
    }


    public List<IssueDataTemplate> issuanceListByIdReader(long id) {
        List<IssueDataTemplate> list;
        try {
            list = webClient.get()
                    .uri(getIssuanceServiceIp() + "/issuance/reader/" + id)
                    .retrieve()
                    .bodyToFlux(IssueDataTemplate.class)
                    .collectList()
                    .block();
        } catch (WebClientResponseException e) {
            throw new NoSuchElementException("Читателю с ID = " + id + " книги не выдавались");
        } catch (Exception e) {
            throw new RuntimeException("Соединение с сервером выдачи книг не установлено");
        }
        return list;
    }


    public IssueDataTemplate createIssuanceDTO(Issue issue) {
        IssueDataTemplate issueTransform = new IssueDataTemplate();
        issueTransform.s(issue.getId());
        issueTransform.setBook(readerBookService.getBookByIdInApi(issue.getBookId()));
        issueTransform.setReader(readerService.getReaderById(issue.getReaderId()));
        issueTransform.setIssuanceAt(issue.getIssuance_at());
        return issueTransform;
    }
}