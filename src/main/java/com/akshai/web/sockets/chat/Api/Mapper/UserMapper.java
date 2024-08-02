package com.akshai.web.sockets.chat.Api.Mapper;

import com.akshai.web.sockets.chat.Api.Model.User;
import com.akshai.web.sockets.chat.Api.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserDTO userDTO);

    UserDTO toDTO(User user);

}
