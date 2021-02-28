package com.websocket.controller;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.websocket.dao.ChatRoomRepository;
import com.websocket.dto.ChatMessage;
import com.websocket.dto.ChatRoom;
import com.websocket.pubsub.RedisPublisher;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ChatController {
	private final RedisPublisher redisPublisher;
	private final ChatRoomRepository chatRoomRepository;

	@MessageMapping("/chat/message")
	public void message(ChatMessage message) {
		if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
			chatRoomRepository.enterChatRoom(message.getRoomId());
			message.setMessage(message.getSender() + "님이 입장하셨습니다.");
		}
		
		redisPublisher.publish(chatRoomRepository.getTopic(message.getRoomId()), message);
	}
}