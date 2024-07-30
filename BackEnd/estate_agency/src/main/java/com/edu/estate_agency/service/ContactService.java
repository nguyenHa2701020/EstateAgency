package com.edu.estate_agency.service;



import java.util.List;

import com.edu.estate_agency.entity.Contact;
import com.edu.estate_agency.model.request.CreateContactRequest;

public interface ContactService {
    Contact save(CreateContactRequest createContactRequest);
    List<Contact> findAll();
    Contact update(String reply, Long id);
}
