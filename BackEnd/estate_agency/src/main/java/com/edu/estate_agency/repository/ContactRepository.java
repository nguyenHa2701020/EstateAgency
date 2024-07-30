package com.edu.estate_agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.estate_agency.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {


    
}
