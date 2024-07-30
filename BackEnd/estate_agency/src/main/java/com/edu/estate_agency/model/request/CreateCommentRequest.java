package com.edu.estate_agency.model.request;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class CreateCommentRequest  {
    private String content;
    private Date date;
    private Long idRoom;
    private Long idCustomer;
    

}
