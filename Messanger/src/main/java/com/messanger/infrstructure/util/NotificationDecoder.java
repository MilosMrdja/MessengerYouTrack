package com.messanger.infrstructure.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.messanger.domain.model.Notification;
import com.messanger.domain.model.NotificationEncoded;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;


public class NotificationDecoder {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static List<Notification> decode(List<NotificationEncoded> encodedList) {
        if (encodedList == null || encodedList.isEmpty()) {
            return List.of();
        }

        return encodedList.stream()
                .map(NotificationDecoder::decodeSingle)
                .collect(Collectors.toList());
    }

    private static Notification decodeSingle(NotificationEncoded encoded) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encoded.getMetadata());
            try (GZIPInputStream gzipStream = new GZIPInputStream(new ByteArrayInputStream(decodedBytes))) {
                JsonNode jsonNode = objectMapper.readTree(gzipStream);
                return objectMapper.treeToValue(jsonNode, Notification.class);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to decode notification with id: " + encoded.getId(), e);
        }
    }
}
