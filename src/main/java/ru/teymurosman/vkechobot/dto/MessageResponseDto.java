package ru.teymurosman.vkechobot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Dto for sending message requests to vk api
 */
@Getter
@Setter
@ToString
@Builder
public class MessageResponseDto {

    @JsonProperty(value = "user_id")
    private long userId;

    @JsonProperty(value = "random_id")
    private int randomId;

    @JsonProperty(value = "peer_id")
    private long peerId;

    private String message;
}
