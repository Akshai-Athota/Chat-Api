package com.akshai.web.sockets.chat.Api.Service;

import com.akshai.web.sockets.chat.Api.Exception.ChatRoomNotFoundException;
import com.akshai.web.sockets.chat.Api.Exception.UserNameNotFoundException;
import com.akshai.web.sockets.chat.Api.dto.ChatRoomDTO;
import com.akshai.web.sockets.chat.Api.dto.UserDTO;

import java.util.List;

public interface ChatRoomService {
    ChatRoomDTO createGroup(ChatRoomDTO chatRoomDTO);
    void addUserToGroup(Long groupId,String username) throws ChatRoomNotFoundException, UserNameNotFoundException;
    void removeUserFromGroup(Long groupId,String username) throws ChatRoomNotFoundException, UserNameNotFoundException;
    void deleteGroup(Long groupId) throws ChatRoomNotFoundException;
    List<UserDTO> getGroupUsers(Long groupId) throws ChatRoomNotFoundException;
}
