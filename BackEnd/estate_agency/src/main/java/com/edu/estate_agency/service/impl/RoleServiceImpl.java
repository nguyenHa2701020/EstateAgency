package com.edu.estate_agency.service.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.estate_agency.entity.ERole;
import com.edu.estate_agency.entity.Role;
import com.edu.estate_agency.repository.RoleRepository;
import com.edu.estate_agency.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        Role rl = new Role();
        rl.setName(role.getName());
        return roleRepository.save(rl);

    }

    @Override
    public Set<Role> findAll() {
       return roleRepository.findALl();
    }

    @Override
    public Optional<Role> findByNameRole(ERole name) {
       return roleRepository.findByName(name);
    }

}
