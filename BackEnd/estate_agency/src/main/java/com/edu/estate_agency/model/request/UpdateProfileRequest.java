package com.edu.estate_agency.model.request;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileRequest {
    private String username;
    private String email;

    private String fullname;

    private String password;

    private String state;

    private String address;

    private String phone;
}
