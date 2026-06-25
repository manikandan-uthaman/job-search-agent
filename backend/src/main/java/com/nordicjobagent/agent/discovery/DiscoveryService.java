package com.nordicjobagent.agent.discovery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscoveryService {

    public final DiscoveryAgent discoveryAgent;

    public List<DiscoveryResponse> discover(DiscoveryRequest request) {
        return discoveryAgent.discover(request);
    }
}
