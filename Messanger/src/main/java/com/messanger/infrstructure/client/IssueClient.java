package com.messanger.infrstructure.client;

import com.messanger.domain.model.IssuePostRequest;
import com.messanger.domain.model.IssuePostResponse;
import com.messanger.domain.model.Project;
import com.messanger.domain.model.User;
import com.messanger.domain.port.AuthPort;
import com.messanger.domain.port.IssuePort;
import com.messanger.domain.port.ProjectPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class IssueClient implements IssuePort {

    private final RestTemplate restTemplate;
    private final String apiBaseUrl;

    public IssuePostResponse createIssue(String summary, String description, String projectId){
        String url = apiBaseUrl + "/issues?fields=idReadable,summary";
        IssuePostRequest issueRequest = IssuePostRequest.create(summary,description,projectId);
        Map<String, Object> requestBody = Map.of(
                "summary", issueRequest.getSummary(),
                "description", issueRequest.getDescription(),
                "project", Map.of("id", issueRequest.getProjectId())
        );

        Map<String, Object> response = restTemplate.postForObject(url, requestBody, Map.class);

        String idReadable = (String) response.get("idReadable");
        String issueSummary = (String) response.get("summary");

        return IssuePostResponse.builder()
                .idReadable(idReadable)
                .summary(issueSummary)
                .build();
    }
}
