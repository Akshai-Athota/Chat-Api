package com.akshai.web.sockets.chat.Api.Mapper;

import com.akshai.web.sockets.chat.Api.Model.User;
import com.akshai.web.sockets.chat.Api.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDTO userDTO);

    UserDTO toDTO(User user);

}
