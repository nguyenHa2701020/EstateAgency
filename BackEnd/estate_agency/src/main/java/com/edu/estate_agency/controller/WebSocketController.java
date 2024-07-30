package com.edu.estate_agency.controller;

import com.edu.estate_agency.model.request.ChatMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/{roomId}")
public ChatMessage chat(@DestinationVariable String roomId, ChatMessage message )
{
    return new ChatMessage(message.getMessage(), message.getUser());
}

}