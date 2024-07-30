package com.edu.estate_agency.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.edu.estate_agency.entity.ERole;
import com.edu.estate_agency.entity.Role;
import com.edu.estate_agency.service.RoleService;
import com.edu.estate_agency.service.UserService;
@Component
public class DataSeeder implements ApplicationListener<ContextRefreshedEvent>{
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
       if(!roleService.findByNameRole(ERole.ROLE_ADMIN).isPresent())
       { 
        roleService.save(new Role(ERole.ROLE_ADMIN));
       }
       if(!roleService.findByNameRole(ERole.ROLE_CUSTOMER).isPresent())
       {
        roleService.save(new Role(ERole.ROLE_CUSTOMER));
       }
       if(!roleService.findByNameRole(ERole.ROLE_AGENT).isPresent())
       {
        roleService.save(new Role(ERole.ROLE_AGENT));
       }

    }
    
}
