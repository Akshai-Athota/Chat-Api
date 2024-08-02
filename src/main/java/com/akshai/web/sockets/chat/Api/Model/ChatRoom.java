package com.akshai.web.sockets.chat.Api.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="groupName")
    private String name;

    @ManyToMany
    @JoinTable(name="chatroom_users",joinColumns = @JoinColumn(name="chat_id"),inverseJoinColumns = @JoinColumn(name = "user-id"))
    private Set<User> users = new HashSet<>();
}
