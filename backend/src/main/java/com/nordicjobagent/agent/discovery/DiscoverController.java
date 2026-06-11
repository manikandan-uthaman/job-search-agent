package com.nordicjobagent.agent.discovery;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/discovery")
@AllArgsConstructor
public class DiscoverController {

    public final DiscoveryAgent discoveryAgent;

    @PostMapping
    public List<DiscoveryResponse> getDiscoveryResponse(@RequestBody DiscoveryRequest request) {
        return discoveryAgent.discover(request.role());
    }
}
