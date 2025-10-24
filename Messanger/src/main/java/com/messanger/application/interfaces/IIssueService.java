package com.messanger.application.interfaces;

import com.messanger.domain.model.IssuePostResponse;

public interface IIssueService {
    IssuePostResponse execute(String summary, String description, String projectId);
}
