package com.akshai.web.sockets.chat.Api.Service.Impl;

import com.akshai.web.sockets.chat.Api.Mapper.MessageMapper;
import com.akshai.web.sockets.chat.Api.Model.Message;
import com.akshai.web.sockets.chat.Api.Repository.MessageRepository;
import com.akshai.web.sockets.chat.Api.Service.MessageService;
import com.akshai.web.sockets.chat.Api.dto.MessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Override
    public MessageDTO saveMessage(MessageDTO messageDto) {
        Message message = messageMapper.toEntity(messageDto);
        messageRepository.save(message);
        return messageMapper.toDTO(message);
    }

    @Override
    public void savePrivateMessage(MessageDTO messageDto, String currentUser) {
        messageDto.setSender(currentUser);
        Message message = messageMapper.toEntity(messageDto);
        messageRepository.save(message);
    }

    @Override
    public List<MessageDTO> getAllMessages() {
        return messageRepository.findAll().stream()
                .map(messageMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MessageDTO> getMessagesByUsername(String username) {
        return messageRepository.findBySenderOrReceiver(username, username).stream()
                .map(messageMapper::toDTO)
                .collect(Collectors.toList());
    }
}
