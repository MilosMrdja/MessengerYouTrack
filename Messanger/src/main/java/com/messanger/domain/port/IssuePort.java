package com.messanger.domain.port;

import com.messanger.domain.model.IssuePostRequest;
import com.messanger.domain.model.IssuePostResponse;

public interface IssuePort {
    IssuePostResponse createIssue(String summary, String description, String projectId);
}
