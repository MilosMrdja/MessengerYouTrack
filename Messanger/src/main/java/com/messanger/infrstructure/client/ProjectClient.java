package com.messanger.infrstructure.client;

import com.messanger.domain.model.Project;
import com.messanger.domain.port.ProjectPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class ProjectClient implements ProjectPort {

    private final RestTemplate restTemplate;
    private final String apiBaseUrl;

    @Override
    public List<Project> getProjects() {
        String url = apiBaseUrl + "/admin/projects?fields=id,name,shortName,team(users(id),id)";
        Project[] projectArray = restTemplate.getForObject(url, Project[].class);
        return projectArray == null ? Collections.emptyList() : Arrays.asList(projectArray);
    }
}
