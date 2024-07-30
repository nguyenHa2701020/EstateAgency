package com.edu.estate_agency.service;

import com.edu.estate_agency.entity.User;
import com.edu.estate_agency.model.request.ChangePasswordRequest;
import com.edu.estate_agency.model.request.CreateUserRequest;
import com.edu.estate_agency.model.request.UpdateProfileRequest;

// import com.example.ogani.entity.User;
// import com.example.ogani.model.request.ChangePasswordRequest;
// import com.example.ogani.model.request.CreateUserRequest;
// import com.example.ogani.model.request.UpdateProfileRequest;

public interface UserService {
    
    void register(CreateUserRequest request);


    User getUserByUsername(String username);

    User updateUser(UpdateProfileRequest request);

    void changePassword(ChangePasswordRequest request);

}
