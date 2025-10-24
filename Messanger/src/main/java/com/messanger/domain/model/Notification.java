package com.messanger.domain.model;

import lombok.Data;

@Data
public class Notification {
    private String type;
    private ChangeNotification change;
    private IssuePostResponse issue;
    private String description;
}
