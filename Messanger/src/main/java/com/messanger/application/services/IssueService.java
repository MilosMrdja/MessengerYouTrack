package com.messanger.application.services;

import com.messanger.application.interfaces.IIssueService;
import com.messanger.domain.model.IssuePostResponse;
import com.messanger.domain.model.Project;
import com.messanger.domain.model.User;
import com.messanger.domain.port.AuthPort;
import com.messanger.domain.port.IssuePort;
import com.messanger.domain.port.ProjectPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueService implements IIssueService {

    private final IssuePort issuerPort;
    private final AuthPort authPort;
    private final ProjectPort projectPort;

    public IssuePostResponse execute(String summary, String description, String projectId) {
        List<Project> projects = projectPort.getProjects();
        User authUser = authPort.getMe();
        validateCreateIssue(projects, projectId, authUser.getId());
        return issuerPort.createIssue(summary, description, projectId);
    }

    private void validateCreateIssue(List<Project> projects, String projectId, String userId) {
        if (projects == null || projects.isEmpty()){
            throw new IllegalArgumentException("There is no projects");
        }
        if(projectId == null) {
            throw new IllegalArgumentException("projectId is null or empty");
        }
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("User ID is null or empty");
        }
        Project project = projects.stream()
                .filter(p -> projectId.equals(p.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Project with ID '" + projectId + "' does not exist"));

        boolean userExists = project.getTeam() != null &&
                project.getTeam().getUsers() != null &&
                project.getTeam().getUsers().stream()
                        .anyMatch(user -> userId.equals(user.getId()));

        if (!userExists) {
            throw new IllegalArgumentException(
                    "User with ID '" + userId + "' is not part of project '" + projectId + "'");
        }
    }
}
