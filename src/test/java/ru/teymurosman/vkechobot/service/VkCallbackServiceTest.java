package ru.teymurosman.vkechobot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.teymurosman.vkechobot.config.VkConfigProperties;
import ru.teymurosman.vkechobot.dto.NewCallbackDto;
import ru.teymurosman.vkechobot.exception.InvalidVkSecretException;
import ru.teymurosman.vkechobot.exception.UnsupportedCallbackTypeException;
import ru.teymurosman.vkechobot.mapper.MessageMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VkCallbackServiceTest {

    @InjectMocks
    VkCallbackService vkCallbackService;

    @Mock
    VkConfigProperties vkConfigProperties;

    @Mock
    MessageMapper messageMapper;

    @Mock
    SenderService senderService;

    NewCallbackDto callbackDto;

    @BeforeEach
    void setUp() {
        callbackDto = new NewCallbackDto();
        callbackDto.setSecret("secret string");
    }

    @Test
    void processRequestConfirmation() {
        callbackDto.setType(NewCallbackDto.CallbackType.confirmation);

        when(vkConfigProperties.getConfirmation())
                .thenReturn("confirmation string");
        when(vkConfigProperties.getSecret())
                .thenReturn("secret string");

        String result = vkCallbackService.processRequest(callbackDto);

        assertEquals("confirmation string", result);
        verify(vkConfigProperties).getConfirmation();
    }

    @Test
    void processRequestMessageNew() {
        callbackDto.setType(NewCallbackDto.CallbackType.message_new);

        when(vkConfigProperties.getSecret())
                .thenReturn("secret string");

        String result = vkCallbackService.processRequest(callbackDto);

        assertEquals("ok", result);
        verify(senderService).sendMessage(any());
    }

    @Test
    void processRequestTypeNull() {
        callbackDto.setType(null);

        when(vkConfigProperties.getSecret())
                .thenReturn("secret string");

        assertThrows(UnsupportedCallbackTypeException.class, () -> vkCallbackService.processRequest(callbackDto));
        verify(senderService, never()).sendMessage(any());
    }

    @Test
    void processRequestWithInvalidSecret() {

        when(vkConfigProperties.getSecret())
                .thenReturn("another secret string");

        assertThrows(InvalidVkSecretException.class, () -> vkCallbackService.processRequest(callbackDto));
        verify(senderService, never()).sendMessage(any());
    }
}