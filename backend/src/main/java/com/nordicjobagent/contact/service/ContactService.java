package com.nordicjobagent.contact.service;

import com.nordicjobagent.contact.domain.Contact;
import com.nordicjobagent.contact.dto.CreateContactRequest;
import com.nordicjobagent.contact.repository.ContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ContactService {

    public final ContactRepository contactRepository;

    public Contact createContact(CreateContactRequest request) {
        return contactRepository.save(mapToContactDomainModel(request));
    }
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
    public Contact getContactById(String id) {
        return contactRepository.findById(UUID.fromString(id)).orElse(null);
    }

    private Contact mapToContactDomainModel(CreateContactRequest request) {
        Contact c = new Contact();
        c.setFirstName(request.firstName());
        c.setLastName(request.lastName());
        c.setTitle(request.title());
        c.setCompany(request.company());
        c.setLocation(request.location());
        c.setLinkedinUrl(request.linkedinUrl());
        c.setCreatedAt(Instant.now());
        return c;
    }
}
