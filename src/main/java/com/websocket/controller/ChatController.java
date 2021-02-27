package com.websocket.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.websocket.dto.ChatRoom;
import com.websocket.service.ChatService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ChatController {
	private final ChatService chatService;

	@PostMapping("/chat")
	public ChatRoom createRoom(@RequestParam String name) {
		return chatService.createRoom(name);
	}

	@GetMapping("/chat")
	public List<ChatRoom> findAllRoom() {
		return chatService.findAllRoom();
	}
}
