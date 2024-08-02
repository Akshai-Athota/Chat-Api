package com.akshai.web.sockets.chat.Api.Service;

import com.akshai.web.sockets.chat.Api.dto.MessageDTO;

import java.util.List;

public interface MessageService {
    MessageDTO saveMessage(MessageDTO messageDTO);
    void savePrivateMessage(MessageDTO messageDTO,String currentUser);
    List<MessageDTO> getAllMessages();
    List<MessageDTO> getMessagesByUsername(String username);
}
