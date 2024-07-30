package com.edu.estate_agency.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateChatRequest {
    private Long idFirstName;
    private Long idSecondName;

}
