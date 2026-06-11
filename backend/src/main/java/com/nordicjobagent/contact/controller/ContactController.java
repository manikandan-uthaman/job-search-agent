package com.nordicjobagent.contact.controller;

import com.nordicjobagent.contact.domain.Contact;
import com.nordicjobagent.contact.dto.CreateContactRequest;
import com.nordicjobagent.contact.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@AllArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public Contact createContact(@RequestBody CreateContactRequest request) {
        return contactService.createContact(request);
    }

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/{id}")
    public Contact getContactById(@PathVariable String id) {
        return contactService.getContactById(id);
    }
}
