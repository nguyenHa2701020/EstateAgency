package com.edu.estate_agency.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edu.estate_agency.entity.ERole;
import com.edu.estate_agency.entity.Role;



@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
       @Query("select p from Role p")
    Set<Role> findALl();
}
