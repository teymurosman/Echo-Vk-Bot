package ru.teymurosman.vkechobot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.teymurosman.vkechobot.dto.NewCallbackDto;
import ru.teymurosman.vkechobot.service.CallbackService;

/**
 * Controller for requests from vk api
 */
@Slf4j
@RestController
@RequestMapping("/vk/callback")
@RequiredArgsConstructor
public class VkCallbackController {

    private final CallbackService callbackService;

    @PostMapping
    public ResponseEntity<String> processCallback(@RequestBody NewCallbackDto newCallbackDto) {
        log.debug("Received new request: {}", newCallbackDto);
        return new ResponseEntity<>(callbackService.processRequest(newCallbackDto), HttpStatus.OK);
    }
}
