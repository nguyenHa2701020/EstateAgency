package com.edu.estate_agency.model.request;

import lombok.Data;

import java.util.Date;
@Data
public class CreateMessageRequest {
    private String senderEmail;
    private Date time= new Date(System.currentTimeMillis());
    private String replymessage;
    private Long idChat;
}
