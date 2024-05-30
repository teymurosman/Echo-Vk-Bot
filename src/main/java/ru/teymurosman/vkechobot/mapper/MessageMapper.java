package ru.teymurosman.vkechobot.mapper;

import org.springframework.stereotype.Component;
import ru.teymurosman.vkechobot.dto.MessageResponseDto;
import ru.teymurosman.vkechobot.dto.NewCallbackDto;
import ru.teymurosman.vkechobot.model.MessageNew;

/**
 * Mapper for message dtos
 */
@Component
public class MessageMapper {

    public MessageNew toMessageNew(NewCallbackDto callbackDto) {
        return MessageNew.builder()
                .id(callbackDto.getObject().getMessage().getId())
                .date(callbackDto.getObject().getMessage().getDate())
                .peerId(callbackDto.getObject().getMessage().getPeerId())
                .fromId(callbackDto.getObject().getMessage().getFromId())
                .text(callbackDto.getObject().getMessage().getText())
                .groupId(callbackDto.getGroupId())
                .build();
    }

    public MessageResponseDto toMessageResponseDto(MessageNew messageNew) {
        return MessageResponseDto.builder()
                .userId(messageNew.getFromId())
                .randomId(0)
                .peerId(messageNew.getPeerId())
                .message(messageNew.getText())
                .build();
    }
}
