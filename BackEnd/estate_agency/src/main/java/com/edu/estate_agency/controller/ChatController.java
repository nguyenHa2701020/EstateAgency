package com.edu.estate_agency.controller;

import com.edu.estate_agency.entity.Chat;
import com.edu.estate_agency.exception.ChatAlreadyExistException;
import com.edu.estate_agency.exception.ChatNotFoundException;
import com.edu.estate_agency.exception.NoChatExistsInTheRepository;
import com.edu.estate_agency.model.request.CreateChatRequest;
import com.edu.estate_agency.model.request.CreateMessageRequest;
import com.edu.estate_agency.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("api/chat")
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class ChatController {
    @Autowired
    private ChatService chatService;

    public ResponseEntity<Chat> creatChat(@RequestParam("idfirstUserName") Long idFristUserName, @RequestParam("idSecondUserName") Long idSecondUserName) {
        CreateChatRequest createChatRequest = new CreateChatRequest();
        createChatRequest.setIdFirstName(idFristUserName);
        createChatRequest.setIdSecondName(idSecondUserName);
        log.info("Hello" + createChatRequest.toString());
        try {
            return new ResponseEntity<Chat>(chatService.addChatV(createChatRequest), HttpStatus.CREATED);

        } catch (ChatAlreadyExistException e) {
            return new ResponseEntity("Chat Already Exist", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/all")
    @Operation(summary = "Get All chat")
    public ResponseEntity<List<Chat>> getAllChat() {
        try {
            return new ResponseEntity<List<Chat>>(chatService.findAllChats(), HttpStatus.OK);
        } catch (NoChatExistsInTheRepository e) {
            return new ResponseEntity("List not found", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id Chat")
    public ResponseEntity<Chat> getChatById(@PathVariable Long id) {
        try {
            return new ResponseEntity<Chat>(chatService.getById(id), HttpStatus.OK);
        } catch (ChatNotFoundException e) {
            return new ResponseEntity("Chat Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/firstUserName/{username}")
    @Operation(summary = "Get Chat By First")
    public ResponseEntity<?> getChatByFirstUserName(@PathVariable String username)
    {
        try
        {
            HashSet<Chat> byChat= this.chatService.getChatByFirstUserName(username);
            return new ResponseEntity<>(byChat, HttpStatus.OK);
        }
        catch (ChatNotFoundException e)
        {
            return new ResponseEntity("Chat not exits", HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/secondUserName/{username}")
    @Operation(summary = "Get Chat By Second")
    public ResponseEntity<?> getChatBySecondUserName(@PathVariable String username)
    {
        try
        {
            HashSet<Chat> byChat= this.chatService.getChatBySecondUserName(username);
            return new ResponseEntity<>(byChat, HttpStatus.OK);
        }
        catch (ChatNotFoundException e)
        {
            return new ResponseEntity("Chat not exits", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/getChatByFirstUserNameOrSecondUserName/{username}")
    @Operation(summary = "Get By User F or S")
    public ResponseEntity<?> getChatByFirstUserNameOrSecondUserName(@PathVariable String username) {

        try {
            HashSet<Chat> byChat = this.chatService.getChatByFirstUserNameOrSecondUserName(username);
            return new ResponseEntity<>(byChat, HttpStatus.OK);
        } catch (ChatNotFoundException e) {
            return new ResponseEntity("Chat Not Exits", HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/getChatByFirstUserNameAndSecondUserName")
    @Operation(summary = "Get By User S or F")
    public ResponseEntity<?> getChatByFirstUserNameAndSecondUserName(@RequestParam("firstUserName") String firstUserName, @RequestParam("secondUserName") String secondUserName){

        try {
            HashSet<Chat> chatByBothEmail = this.chatService.getChatByFirstUserNameAndSecondUserName(firstUserName, secondUserName);
            return new ResponseEntity<>(chatByBothEmail, HttpStatus.OK);
        } catch (ChatNotFoundException e) {
            return new ResponseEntity("Chat Not Exits", HttpStatus.NOT_FOUND);
        }
    }
@PutMapping("/message/{chatId}")
    @Operation(summary = "Create message")
    public ResponseEntity<Chat> addMessage(@RequestParam("message") String message,@RequestParam("acount") String acount, @PathVariable Long chatId) throws ChatNotFoundException{
    log.info("Hello"+chatId.toString());
    CreateMessageRequest message2=new CreateMessageRequest();
    message2.setSenderEmail(acount);
    message2.setIdChat(chatId);
    message2.setReplymessage(message);
    log.info(message2.toString());
    return new ResponseEntity<Chat>(chatService.addMessageV(message2), HttpStatus.OK);
}
}
