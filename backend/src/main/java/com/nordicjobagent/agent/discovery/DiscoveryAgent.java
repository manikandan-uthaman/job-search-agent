package com.nordicjobagent.agent.discovery;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DiscoveryAgent {

    private final ChatClient chatClient;

    public DiscoveryAgent(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public List<DiscoveryResponse> discover(String role) {
        log.info("Running discovery for role {}", role);
        String prompt = """
                You are a recruitment research assistant.

                Suggest 5 relevant contacts
                for a %s seeking jobs in Sweden.

                Return:
                - name
                - title
                - company
                - reason
                """.formatted(role);

        return chatClient
                .prompt(prompt)
                .call()
                .entity(new ParameterizedTypeReference<>() {
                });
    }
}
