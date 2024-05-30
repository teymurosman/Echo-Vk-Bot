package ru.teymurosman.vkechobot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.teymurosman.vkechobot.config.VkConfigProperties;
import ru.teymurosman.vkechobot.dto.MessageResponseDto;
import ru.teymurosman.vkechobot.dto.ResponseFromVkDto;
import ru.teymurosman.vkechobot.exception.SenderException;

import java.net.URI;
import java.util.Objects;

/**
 * Implementation of a SenderService that creates URI needed by vk api and sends messages
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class VkMessageSenderService implements SenderService {

    private static final String URL_SEND = "https://api.vk.com/method/messages.send";

    private final RestTemplate restTemplate;
    private final VkConfigProperties vkConfigProperties;
    private final ObjectMapper objectMapper;

    public void sendMessage(MessageResponseDto messageResponseDto) {
        log.debug("Sending message: {}", messageResponseDto);

        ResponseEntity<ResponseFromVkDto> response = restTemplate
                .postForEntity(createUri(messageResponseDto), null, ResponseFromVkDto.class);
        checkResponse(response);
    }

    private URI createUri(MessageResponseDto messageResponseDto) {
        MultiValueMap<String, String> params = objectMapper.convertValue(messageResponseDto, LinkedMultiValueMap.class);
        return UriComponentsBuilder.fromHttpUrl(URL_SEND)
                .queryParam("access_token", vkConfigProperties.getToken())
                .queryParam("v", vkConfigProperties.getVersion())
                .queryParams(params)
                .build()
                .toUri();
    }

    private void checkResponse(ResponseEntity<ResponseFromVkDto> response) {
        log.debug("Response from vk: {}", response.getBody());

        ResponseFromVkDto.ResponseError error = Objects.requireNonNull(response.getBody()).getError();
        if (error != null) {
            throw new SenderException(error.toString());
        }
    }
}
