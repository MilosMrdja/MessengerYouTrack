package com.messanger.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ChangeNotification {
    private String humanReadableTimeStamp;
    private Long endTimestamp;
    private List<EventNotification> events;
}
