package com.nordicjobagent.contact.dto;

public record CreateContactRequest(
        String firstName,
        String lastName,
        String title,
        String company,
        String location,
        String linkedinUrl
) {}
