package com.edu.estate_agency.model.request;



import java.util.Date;


import lombok.Data;
import lombok.Getter;

@Data

public class CreateContactRequest {
    private String email;
    private String title;
    private String content;
    private String reply;
    private Date dayContact;
    private Date dayReply;
    private String status;
    private long idUser;
  
}
