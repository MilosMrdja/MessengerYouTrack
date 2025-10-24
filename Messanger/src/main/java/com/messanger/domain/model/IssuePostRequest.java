package com.messanger.domain.model;

import lombok.*;

@Getter
public class IssuePostRequest {
    private String summary;
    private String description;
    private String projectId;

    private IssuePostRequest(String summary, String description, String projectId) {
        this.summary = summary;
        this.description = description;
        this.projectId = projectId;
    }

    public static IssuePostRequest create(String summary, String description, String projectId) {
        if (summary == null || summary.isBlank()) {
            throw new IllegalArgumentException("Summary must not be empty.");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("description must not be empty.");
        }
        if (projectId == null || projectId.isBlank()) {
            throw new IllegalArgumentException("Project ID must not be empty.");
        }

        return new IssuePostRequest(summary.trim(), description, projectId);
    }
}
