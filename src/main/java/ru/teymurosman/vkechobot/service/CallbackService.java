package ru.teymurosman.vkechobot.service;

import ru.teymurosman.vkechobot.dto.NewCallbackDto;

/**
 * Service that handles callback requests from vk api
 */
public interface CallbackService {

    /**
     * Processes callback and returns needed by vk api response
     * @param callbackDto received callback from request
     * @return string for response to vk api
     */
    String processRequest(NewCallbackDto callbackDto);
}
