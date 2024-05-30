package ru.teymurosman.vkechobot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Dto for response from vk api after sending message
 */
@Getter
@Setter
@ToString
public class ResponseFromVkDto {

    @JsonProperty(value = "peer_id")
    private long peerId;

    @JsonProperty(value = "message_id")
    private long messageId;

    @JsonProperty(value = "conversation_message_id")
    private long conversationMessageId;

    private ResponseError error;

    @Getter
    @Setter
    @ToString
    public static class ResponseError {
        @JsonProperty(value = "error_code")
        private long errorCode;
        @JsonProperty(value = "error_message")
        private String errorMessage;
    }
}
