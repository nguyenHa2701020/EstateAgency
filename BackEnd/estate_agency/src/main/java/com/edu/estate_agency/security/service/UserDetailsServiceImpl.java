package com.edu.estate_agency.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edu.estate_agency.entity.User;
import com.edu.estate_agency.repository.UserRepository;

import jakarta.transaction.Transactional;

// import com.example.ogani.entity.User;
// import com.example.ogani.repository.UserRepository;




@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        // if(user != null && user.isEnabled()){
            
        //     return UserDetailsImpl.build(user);
        // }else{
        //     throw new UsernameNotFoundException("User Not Found with username: " + username);
        // }

        return UserDetailsImpl.build(user);

        
    }
    
}