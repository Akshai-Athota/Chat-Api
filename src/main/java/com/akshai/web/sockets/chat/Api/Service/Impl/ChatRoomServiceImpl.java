package com.akshai.web.sockets.chat.Api.Service.Impl;

import com.akshai.web.sockets.chat.Api.Exception.ChatRoomNotFoundException;
import com.akshai.web.sockets.chat.Api.Exception.UserNameNotFoundException;
import com.akshai.web.sockets.chat.Api.Mapper.ChatRoomMapper;
import com.akshai.web.sockets.chat.Api.Mapper.UserMapper;
import com.akshai.web.sockets.chat.Api.Model.ChatRoom;
import com.akshai.web.sockets.chat.Api.Model.User;
import com.akshai.web.sockets.chat.Api.Repository.ChatRoomRepository;
import com.akshai.web.sockets.chat.Api.Repository.UserRepository;
import com.akshai.web.sockets.chat.Api.Service.ChatRoomService;
import com.akshai.web.sockets.chat.Api.dto.ChatRoomDTO;
import com.akshai.web.sockets.chat.Api.dto.UserDTO;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final ChatRoomMapper chatRoomMapper;
    private final UserMapper userMapper;
    @Override
    public ChatRoomDTO createGroup(ChatRoomDTO chatRoomDTO) {
        ChatRoom chatRoom=chatRoomMapper.toEntity(chatRoomDTO);
        return chatRoomMapper.toDTO(chatRoomRepository.save(chatRoom));
    }

    @Override
    public void addUserToGroup(Long groupId, String username) throws ChatRoomNotFoundException, UserNameNotFoundException {
        Optional<ChatRoom> opChatRoom = chatRoomRepository.findById(groupId);
        Optional<User> opUser=userRepository.findByUsername(username);
        if(opChatRoom.isEmpty()){
            throw  new ChatRoomNotFoundException();
        }
        if(opUser.isEmpty()){
            throw  new UserNameNotFoundException();
        }
        ChatRoom chatRoom=opChatRoom.get();
        chatRoom.getUsers().add(opUser.get());
        chatRoomRepository.save(chatRoom);
    }

    @Override
    public void removeUserFromGroup(Long groupId, String username) throws ChatRoomNotFoundException, UserNameNotFoundException {
        Optional<ChatRoom> opChatRoom = chatRoomRepository.findById(groupId);
        Optional<User> opUser=userRepository.findByUsername(username);
        if(opChatRoom.isEmpty()){
            throw  new ChatRoomNotFoundException();
        }
        if(opUser.isEmpty()){
            throw  new UserNameNotFoundException();
        }
        ChatRoom chatRoom=opChatRoom.get();
        chatRoom.getUsers().remove(opUser.get());
        chatRoomRepository.save(chatRoom);
    }

    @Override
    public void deleteGroup(Long groupId) throws ChatRoomNotFoundException {
        Optional<ChatRoom> opChatRoom = chatRoomRepository.findById(groupId);
        if(opChatRoom.isEmpty()){
            throw  new ChatRoomNotFoundException();
        }
        chatRoomRepository.deleteById(groupId);
    }

    @Override
    public List<UserDTO> getGroupUsers(Long groupId) throws ChatRoomNotFoundException {
        Optional<ChatRoom> opChatRoom = chatRoomRepository.findById(groupId);
        if(opChatRoom.isEmpty()){
            throw  new ChatRoomNotFoundException();
        }
        ChatRoom chatRoom=opChatRoom.get();
        return chatRoom.getUsers().stream().map(userMapper::toDTO).toList();
    }
}
