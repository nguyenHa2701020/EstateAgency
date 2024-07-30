package com.edu.estate_agency.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.estate_agency.entity.Contact;
import com.edu.estate_agency.model.request.CreateContactRequest;
import com.edu.estate_agency.repository.ContactRepository;
import com.edu.estate_agency.repository.UserRepository;
import com.edu.estate_agency.service.ContactService;
import com.edu.estate_agency.utils.Email;
import com.edu.estate_agency.utils.EmailUlti;

import lombok.AllArgsConstructor;


@Service

public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  Email emails;

    @Override
    public Contact save(CreateContactRequest createContactRequest) {
        Contact contact = new Contact();
        contact.setTitle(createContactRequest.getTitle());
        contact.setContent(createContactRequest.getContent());
        contact.setDayContact(new Date());
        contact.setUser(userRepository.findById(createContactRequest.getIdUser()).get());
        contact.setStatus("Chờ phản hồi");
        contact.setEmail(userRepository.findById(createContactRequest.getIdUser()).get().getEmail());
        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact update(String reply, Long id) {
        Contact contact = contactRepository.findById(id).get();
  
    emails.sendEmail(contact.getEmail(), contact.getTitle(), reply);
    
        contact.setReply(reply);
        contact.setDayReply(new Date());
        contact.setStatus("Đã phản hồi");
        return contactRepository.save(contact);
    }

}
