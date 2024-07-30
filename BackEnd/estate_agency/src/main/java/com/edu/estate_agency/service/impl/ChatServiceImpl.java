package com.edu.estate_agency.service.impl;

import com.edu.estate_agency.entity.Chat;
import com.edu.estate_agency.entity.Message;
import com.edu.estate_agency.exception.ChatAlreadyExistException;
import com.edu.estate_agency.exception.ChatNotFoundException;
import com.edu.estate_agency.exception.NoChatExistsInTheRepository;
import com.edu.estate_agency.model.request.CreateChatRequest;
import com.edu.estate_agency.model.request.CreateMessageRequest;
import com.edu.estate_agency.repository.ChatRepository;
import com.edu.estate_agency.repository.UserRepository;
import com.edu.estate_agency.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Chat addChat(Chat chat) throws ChatAlreadyExistException {
        return chatRepository.save(chat);
    }

    @Override
    public Chat addChatV(CreateChatRequest chat) throws ChatAlreadyExistException {
        Chat chats = new Chat();
        chats.setFirstUserName(userRepository.findById(chat.getIdFirstName()).get());
        chats.setSecondUserName(userRepository.findById(chat.getIdSecondName()).get());
        chats.setMessageList(new ArrayList<>());

        return chatRepository.save(chats);
    }

    @Override
    public List<Chat> findAllChats() throws NoChatExistsInTheRepository {
        if (chatRepository.findAll().isEmpty()) {
            throw new NoChatExistsInTheRepository();
        } else {
            return chatRepository.findAll();
        }

    }

    @Override
    public Chat getById(Long id) throws ChatNotFoundException {
        Optional<Chat> chatid = chatRepository.findById(id);
        if (chatid.isPresent()) {
            return chatid.get();
        } else {
            throw new ChatNotFoundException();
        }


    }

    @Override
    public HashSet<Chat> getChatByFirstUserName(String username) throws ChatNotFoundException {
        HashSet<Chat> chat = chatRepository.getChatByFirstUserName(username);
        if (chat.isEmpty()) {
            throw new ChatNotFoundException();
        } else {
            return chat;
        }

    }

    @Override
    public HashSet<Chat> getChatBySecondUserName(String username) throws ChatNotFoundException {
        HashSet<Chat> chat = chatRepository.getChatBySecondUserName(username);
        if (chat.isEmpty()) {
            throw new ChatNotFoundException();
        } else {
            return chat;
        }
    }

    @Override
    public HashSet<Chat> getChatByFirstUserNameOrSecondUserName(String username) throws ChatNotFoundException {
        HashSet<Chat> chat = chatRepository.getChatByFirstUserName(username);
        HashSet<Chat> chat1 = chatRepository.getChatBySecondUserName(username);
        chat1.addAll(chat);
        if (chat.isEmpty() && chat1.isEmpty()) {
            throw new ChatNotFoundException();

        } else if (chat1.isEmpty()) {
            return chat;

        } else {
            return chat;
        }

    }

    @Override
    public HashSet<Chat> getChatByFirstUserNameAndSecondUserName(String firsUserName, String secondUserName) throws ChatNotFoundException {
        HashSet<Chat> chat = chatRepository.getChatByFirstUserNameAndSecondUserName(firsUserName, secondUserName);
        HashSet<Chat> chat1 = chatRepository.getChatByFirstUserNameAndSecondUserName(firsUserName, secondUserName);

        if (chat.isEmpty() && chat1.isEmpty()) {
            throw new ChatNotFoundException();

        } else if (chat.isEmpty()) {
            return chat1;

        } else {
            return chat;
        }
    }

    @Override
    public Chat addMessage(Message add, Long chatId) throws ChatNotFoundException {
        Optional<Chat> chat = chatRepository.findById(chatId);
        Chat chat2 = chat.get();
        if (chat2.getMessageList() == null) {
            List<Message> msg = new ArrayList<>();
            msg.add(add);
            chat2.setMessageList(msg);
            return chatRepository.save(chat2);
        } else {
            List<Message> rates = chat2.getMessageList();
            rates.add(add);
            chat2.setMessageList(rates);
            return chatRepository.save(chat2);
        }


    }

    @Override
    public Chat addMessageV(CreateMessageRequest createMessageRequest) throws ChatNotFoundException {
        Optional<Chat> chat = chatRepository.findById(createMessageRequest.getIdChat());
        Chat chats = chat.get();
        Message message = new Message();
        message.setSenderEmail(createMessageRequest.getSenderEmail());
        message.setReplymessage(createMessageRequest.getReplymessage());
        message.setChat(chats);
        if (chats.getMessageList() == null) {
            List<Message> msg = new ArrayList<>();
            msg.add(message);
            chats.setMessageList(msg);
            return chatRepository.save(chats);
        }
        else {
            List<Message> rates= chats.getMessageList();
            rates.add(message);
            chats.setMessageList(rates);
            return chatRepository.save(chats);
        }

    }
}

