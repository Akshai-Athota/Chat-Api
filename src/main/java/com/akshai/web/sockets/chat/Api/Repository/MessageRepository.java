package com.akshai.web.sockets.chat.Api.Repository;

import com.akshai.web.sockets.chat.Api.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
    List<Message> findBySenderOrReceiver(String sender,String Receiver);
}
