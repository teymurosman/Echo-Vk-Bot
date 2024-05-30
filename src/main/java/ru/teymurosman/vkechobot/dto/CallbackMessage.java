package ru.teymurosman.vkechobot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Message part of an object from vk api
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CallbackMessage {
    private long date; // Unix-time
    @JsonProperty(value = "from_id")
    private long fromId;
    private long id;
    private String text;
    @JsonProperty(value = "peer_id")
    private long peerId;
}
