package com.edu.estate_agency.model.request;


import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder

public class CreateRoomRequest {
private Long id;
private String name;
private Float price;
private String address;
private String describe;
private String state;
private MultipartFile img;
private boolean enabled;
private Long idAgent;    
}
