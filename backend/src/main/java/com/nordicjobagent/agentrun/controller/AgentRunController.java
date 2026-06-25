package com.nordicjobagent.agentrun.controller;

import com.nordicjobagent.agentrun.domain.AgentRun;
import com.nordicjobagent.agentrun.service.AgentRunService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/agent-runs")
@AllArgsConstructor
public class AgentRunController {

    private final AgentRunService agentRunService;

    @GetMapping
    public List<AgentRun> getAgentRun() {
        return agentRunService.findAll();
    }
}
