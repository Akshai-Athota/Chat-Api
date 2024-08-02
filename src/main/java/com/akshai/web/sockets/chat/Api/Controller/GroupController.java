package com.akshai.web.sockets.chat.Api.Controller;

import com.akshai.web.sockets.chat.Api.Exception.ChatRoomNotFoundException;
import com.akshai.web.sockets.chat.Api.Exception.UserNameNotFoundException;
import com.akshai.web.sockets.chat.Api.Service.ChatRoomService;
import com.akshai.web.sockets.chat.Api.dto.ChatRoomDTO;
import com.akshai.web.sockets.chat.Api.dto.UserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {
    private final ChatRoomService chatRoomService;

    @PostMapping
    public ResponseEntity<ChatRoomDTO> createGroup(@Valid @RequestBody ChatRoomDTO chatRoomDto) {
        return ResponseEntity.ok(chatRoomService.createGroup(chatRoomDto));
    }

    @PostMapping("/{groupId}/addUser")
    public ResponseEntity<String> addUserToGroup(@PathVariable Long groupId, @RequestParam String username) {
        try {
            chatRoomService.addUserToGroup(groupId, username);
        } catch (ChatRoomNotFoundException | UserNameNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("User added to group successfully");
    }

    @PostMapping("/{groupId}/removeUser")
    public ResponseEntity<String> removeUserFromGroup(@PathVariable Long groupId, @RequestParam String username) {
        try {
            chatRoomService.removeUserFromGroup(groupId, username);
        } catch (ChatRoomNotFoundException | UserNameNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("User removed from group successfully");
    }

    @PostMapping("/{groupId}/leave")
    public ResponseEntity<String> leaveGroup(@PathVariable Long groupId, @RequestParam String username) {
        try {
            chatRoomService.removeUserFromGroup(groupId, username);
        } catch (ChatRoomNotFoundException | UserNameNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("User left the group successfully");
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<String> deleteGroup(@PathVariable Long groupId) {
        try {
            chatRoomService.deleteGroup(groupId);
        } catch (ChatRoomNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("Group deleted successfully");
    }

    @GetMapping("/{groupId}/users")
    public ResponseEntity<List<UserDTO>> getGroupUsers(@PathVariable Long groupId) {
        try {
            return ResponseEntity.ok(chatRoomService.getGroupUsers(groupId));
        } catch (ChatRoomNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
