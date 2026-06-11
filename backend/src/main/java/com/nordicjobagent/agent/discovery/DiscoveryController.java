package com.nordicjobagent.agent.discovery;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/discovery")
@AllArgsConstructor
public class DiscoveryController {

    public final DiscoveryService discoveryService;

    @PostMapping
    public List<DiscoveryResponse> getDiscoveryResponse(@Valid @RequestBody DiscoveryRequest request) {
        return discoveryService.discover(request);
    }
}
