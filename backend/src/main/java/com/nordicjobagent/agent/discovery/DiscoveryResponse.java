package com.nordicjobagent.agent.discovery;

public record DiscoveryResponse(
        String company,
        String reason,
        Integer opportunityScore
) {
}
