package com.edu.estate_agency.service;

import com.edu.estate_agency.entity.Chat;
import com.edu.estate_agency.entity.Message;
import com.edu.estate_agency.exception.ChatAlreadyExistException;
import com.edu.estate_agency.exception.ChatNotFoundException;
import com.edu.estate_agency.exception.NoChatExistsInTheRepository;
import com.edu.estate_agency.model.request.CreateChatRequest;
import com.edu.estate_agency.model.request.CreateMessageRequest;

import java.util.HashSet;
import java.util.List;

public interface ChatService {
    public Chat addChat(Chat chat) throws ChatAlreadyExistException;
    public Chat addChatV(CreateChatRequest chat) throws ChatAlreadyExistException;
    List<Chat> findAllChats() throws NoChatExistsInTheRepository;
    Chat getById(Long id) throws ChatNotFoundException;
    HashSet<Chat> getChatByFirstUserName(String username) throws ChatNotFoundException;
    HashSet<Chat> getChatBySecondUserName(String username) throws ChatNotFoundException;
    HashSet<Chat> getChatByFirstUserNameOrSecondUserName(String username) throws ChatNotFoundException;
    HashSet<Chat> getChatByFirstUserNameAndSecondUserName(String firsUserName, String secondUserName) throws ChatNotFoundException;
    Chat addMessage(Message add, Long chatId) throws ChatNotFoundException;
    Chat addMessageV(CreateMessageRequest createMessageRequest) throws ChatNotFoundException;


}
