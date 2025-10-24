package com.messanger.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class EventNotification {
    private CategoryEventNotification category;
    @JsonProperty("addedValues")
    private List<Map<String, Object>> addedValues;

    public String getDescription() {
        if (addedValues != null && !addedValues.isEmpty()) {
            Object name = addedValues.get(0).get("name");
            return name != null ? name.toString().trim() : null;
        }
        return null;
    }
}
