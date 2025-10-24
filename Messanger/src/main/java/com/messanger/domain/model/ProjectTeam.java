package com.messanger.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class ProjectTeam {
    private String id;
    private List<User> users;
}
