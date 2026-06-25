package com.nordicjobagent.agentrun.repository;

import com.nordicjobagent.agentrun.domain.AgentRun;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AgentRunRepository
        extends JpaRepository<AgentRun, UUID> {
}
