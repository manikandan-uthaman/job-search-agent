package com.nordicjobagent.agent.discovery;

import jakarta.validation.constraints.NotBlank;

public record DiscoveryRequest(
        @NotBlank
        String role,
        String country,
        String skills,
        String experience
) {
}
