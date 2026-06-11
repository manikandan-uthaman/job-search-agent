package com.nordicjobagent.agent.discovery;

public record DiscoveryResponse(
        String name,
        String title,
        String company,
        String reason
) {
}
