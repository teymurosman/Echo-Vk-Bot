package ru.teymurosman.vkechobot.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Model of vk message
 */
@Getter
@Setter
@Builder
public class MessageNew {

    private long id;

    private long date; // Unix-time

    private long peerId;

    private long fromId;

    private String text;

    private long groupId;
}
