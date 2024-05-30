package ru.teymurosman.vkechobot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Dto for receiving callbacks from vk api
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewCallbackDto {

    private CallbackType type;

    private CallbackBody object;

    @JsonProperty(value = "group_id")
    private Long groupId;

    private String secret;

    public enum CallbackType {
        confirmation, message_new
    }
}
