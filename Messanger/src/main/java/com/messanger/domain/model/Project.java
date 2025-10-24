package com.messanger.domain.model;

import lombok.Data;

@Data
public class Project {
    private String id;
    private String name;
    private String shortName;
    private ProjectTeam team;
}
