package com.akshai.web.sockets.chat.Api.Controller;

import com.akshai.web.sockets.chat.Api.Service.MessageService;
import com.akshai.web.sockets.chat.Api.dto.MessageDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Data
@AllArgsConstructor
@Controller
public class ChatController {
    private final MessageService messageService;

    @MessageMapping("/chat")
    @SendTo("/user")
    public MessageDTO sendMessage(@Payload MessageDTO messageDto) {
        return messageService.saveMessage(messageDto);
    }

    @MessageMapping("/private")
    public void sendPrivateMessage(@Payload MessageDTO messageDto, Principal principal) {
        messageService.savePrivateMessage(messageDto, principal.getName());
    }

    @GetMapping("/api/messages")
    public ResponseEntity<List<MessageDTO>> getAllMessages() {
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    @GetMapping("/api/messages/user")
    public ResponseEntity<List<MessageDTO>> getMessagesByUsername(@RequestParam String username) {
        return ResponseEntity.ok(messageService.getMessagesByUsername(username));
    }

}
