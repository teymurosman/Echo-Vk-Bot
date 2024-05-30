package ru.teymurosman.vkechobot.service;

import ru.teymurosman.vkechobot.dto.MessageResponseDto;

/**
 * Service that sends messages to vk api
 */
public interface SenderService {

    /**
     * Sends messages
     * @param messageResponseDto object that will be sent
     */
    void sendMessage(MessageResponseDto messageResponseDto);
}
