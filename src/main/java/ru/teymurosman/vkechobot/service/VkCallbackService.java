package ru.teymurosman.vkechobot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.teymurosman.vkechobot.config.VkConfigProperties;
import ru.teymurosman.vkechobot.dto.MessageResponseDto;
import ru.teymurosman.vkechobot.dto.NewCallbackDto;
import ru.teymurosman.vkechobot.exception.InvalidVkSecretException;
import ru.teymurosman.vkechobot.exception.UnsupportedCallbackTypeException;
import ru.teymurosman.vkechobot.mapper.MessageMapper;
import ru.teymurosman.vkechobot.model.MessageNew;

/**
 * Implementation of a CallbackService that handles 'confirmation' and 'message_new' types of callbacks
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class VkCallbackService implements CallbackService {

    private final VkConfigProperties vkConfigProperties;
    private final MessageMapper messageMapper;
    private final SenderService senderService;

    @Override
    public String processRequest(NewCallbackDto callbackDto) {
        log.debug("Processing request of type: {}", callbackDto.getType());

        validateVkSecret(callbackDto.getSecret());
        switch (callbackDto.getType()) {
            case confirmation -> {
                return vkConfigProperties.getConfirmation();
            }
            case message_new -> {
                MessageNew message = messageMapper.toMessageNew(callbackDto);
                send(message);
                return "ok";
            }
            case null, default -> throw new UnsupportedCallbackTypeException(
                    "Тип запросов " + callbackDto.getType() + " не поддерживается");
        }
    }

    private void send(MessageNew message) {
        MessageResponseDto messageResponseDto = messageMapper.toMessageResponseDto(message);
        senderService.sendMessage(messageResponseDto);
    }

    private void validateVkSecret(String secret) {
        if (!secret.equals(vkConfigProperties.getSecret())) {
            throw new InvalidVkSecretException("Invalid VkSecret: " + secret);
        }
    }
}
