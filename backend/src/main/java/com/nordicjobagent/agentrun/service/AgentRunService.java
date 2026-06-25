package com.nordicjobagent.agentrun.service;

import com.nordicjobagent.agentrun.domain.AgentRun;
import com.nordicjobagent.agentrun.repository.AgentRunRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class AgentRunService {

    private final AgentRunRepository agentRunRepository;

    public AgentRun saveRun(String agentName, String query, String prompt, String response) {
        return agentRunRepository.save(AgentRun.builder()
                .agentName(agentName)
                .userQuery(query)
                .prompt(prompt)
                .response(response)
                .createdAt(Instant.now())
                .build());
    }

    public List<AgentRun> findAll() {
        return agentRunRepository.findAll();
    }
}
