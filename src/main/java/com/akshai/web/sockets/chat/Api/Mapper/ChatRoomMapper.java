package com.akshai.web.sockets.chat.Api.Mapper;

import com.akshai.web.sockets.chat.Api.Model.ChatRoom;
import com.akshai.web.sockets.chat.Api.dto.ChatRoomDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ChatRoomMapper {

    ChatRoomMapper INSTANCE = Mappers.getMapper(ChatRoomMapper.class);

    ChatRoom toEntity(ChatRoomDTO chatRoomDTO);
    ChatRoomDTO toDTO(ChatRoom chatRoom);
}
