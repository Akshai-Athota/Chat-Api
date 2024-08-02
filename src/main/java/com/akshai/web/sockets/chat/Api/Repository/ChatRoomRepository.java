package com.akshai.web.sockets.chat.Api.Repository;

import com.akshai.web.sockets.chat.Api.Model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {
}
