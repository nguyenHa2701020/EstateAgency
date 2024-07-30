package com.edu.estate_agency.model.request;

import lombok.Data;

@Data
public class CreateBillRequest {
    private String name;
    private Long idContract;

}
