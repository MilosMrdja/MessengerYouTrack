package com.messanger.domain.model;

import lombok.Data;

@Data
public class NotificationEncoded {
    private String id;
    private String metadata;
    private User recipient;
}
