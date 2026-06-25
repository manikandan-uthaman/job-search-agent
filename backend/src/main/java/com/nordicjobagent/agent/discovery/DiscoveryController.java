package com.nordicjobagent.agent.discovery;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/discovery")
@AllArgsConstructor
public class DiscoveryController {

    public final DiscoveryService discoveryService;

    @PostMapping
    public List<DiscoveryResponse> getDiscoveryResponse(@RequestBody @Valid DiscoveryRequest request) {
        return discoveryService.discover(request);
    }
}
