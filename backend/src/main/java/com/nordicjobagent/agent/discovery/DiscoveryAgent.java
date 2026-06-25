package com.nordicjobagent.agent.discovery;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nordicjobagent.agent.tool.CompanySearchTool;
import com.nordicjobagent.agent.tool.SemanticCompanySearchTool;
import com.nordicjobagent.agentrun.service.AgentRunService;
import com.nordicjobagent.embedding.EmbeddingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DiscoveryAgent {

    private final ChatClient chatClient;
    private final EmbeddingService embeddingService;
    private final AgentRunService agentRunService;


    public DiscoveryAgent(ChatClient.Builder chatClientBuilder, EmbeddingService embeddingService, AgentRunService agentRunService) {
        this.chatClient = chatClientBuilder.build();
        this.embeddingService = embeddingService;
        this.agentRunService = agentRunService;
    }

    public List<DiscoveryResponse> discover(DiscoveryRequest request) {

        String searchQuery = """
                    %s
                    %s
                    %s
                    %s
                    """
                .formatted(
                        request.role(),
                        request.country(),
                        request.skills(),
                        request.experience()
                );

        List<DiscoveryContext> companies =
                embeddingService
                        .findRelevantCompanies(
                                searchQuery
                        );

        String companyContext =
                companies.stream()
                        .map(company -> """
                        Company: %s
                        Country: %s

                        Details:
                        %s
                        """
                                .formatted(
                                        company.companyName(),
                                        company.country(),
                                        company.details()
                                ))
                        .collect(
                                Collectors.joining(
                                        "\n\n-------------------\n\n"
                                )
                        );

        String prompt = """
                You are a Nordic recruitment advisor.
                
                Candidate Profile
                
                Role: %s
                
                Country: %s
                
                Skills: %s
                
                Experience: %s
                
                Use ONLY the following companies.
                
                %s
                
                Recommend the 3 best opportunities.
                
                Return:
                - company
                - reason
                - opportunityScore
                """
                .formatted(
                        request.role(),
                        request.country(),
                        request.skills(),
                        request.experience(),
                        companyContext
                );

        List<DiscoveryResponse> recommendations = chatClient
                .prompt(prompt)
                .call()
                .entity(
                        new ParameterizedTypeReference<
                                List<DiscoveryResponse>>() {}
                );

        try {
            agentRunService.saveRun(
                    "DISCOVERY_AGENT",
                    searchQuery,
                    prompt,
                    new ObjectMapper().writeValueAsString(
                            recommendations
                    )
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return recommendations;
    }

}
