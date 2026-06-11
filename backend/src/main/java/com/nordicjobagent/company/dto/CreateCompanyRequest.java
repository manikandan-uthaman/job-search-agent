package com.nordicjobagent.company.dto;

import java.time.Instant;
import java.util.UUID;

public record CreateCompanyRequest (
        String name,
        String website,
        String headquarters,
        String country,
        Boolean hiring,
        Integer score,
        Instant lastResearchedAt
){
}
