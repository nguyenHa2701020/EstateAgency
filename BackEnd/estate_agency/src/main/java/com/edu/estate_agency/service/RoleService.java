package com.edu.estate_agency.service;

import java.util.Optional;
import java.util.Set;

import com.edu.estate_agency.entity.ERole;
import com.edu.estate_agency.entity.Role;

public interface RoleService {
    Role save(Role role);
    Set<Role> findAll();
    Optional<Role> findByNameRole(ERole name);
}
