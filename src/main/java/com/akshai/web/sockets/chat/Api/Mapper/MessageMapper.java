package com.akshai.web.sockets.chat.Api.Mapper;

import com.akshai.web.sockets.chat.Api.Model.Message;
import com.akshai.web.sockets.chat.Api.dto.MessageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    Message toEntity(MessageDTO messageDTO);
    MessageDTO toDTO(Message message);
}
