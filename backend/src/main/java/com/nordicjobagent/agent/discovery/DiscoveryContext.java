package com.nordicjobagent.agent.discovery;

public record DiscoveryContext(
        String companyId,
        String companyName,
        String country,
        String details
) {
}