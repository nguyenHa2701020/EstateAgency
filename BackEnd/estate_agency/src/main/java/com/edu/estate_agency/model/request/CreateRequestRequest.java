package com.edu.estate_agency.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class CreateRequestRequest {
    private long id;
    private long iduser;

    private long idroom;

    private String status;

    private String description;

    private Boolean browse;

    private Date date;
}
