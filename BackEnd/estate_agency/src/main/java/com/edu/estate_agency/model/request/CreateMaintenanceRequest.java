package com.edu.estate_agency.model.request;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CreateMaintenanceRequest {
     private String name;

    private Date date;

    private Long price;

    private MultipartFile file;

    private Long idroom;
}
