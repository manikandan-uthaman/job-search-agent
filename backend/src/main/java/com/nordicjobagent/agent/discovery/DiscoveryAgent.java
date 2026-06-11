package com.nordicjobagent.agent.discovery;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscoveryAgent {

    private final ChatClient chatClient;

    public DiscoveryAgent(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public List<DiscoveryResponse> discover(String role) {

        String prompt = """
                You are a recruitment research assistant.

                Suggest 5 relevant contacts
                for a Senior Java Engineer seeking jobs in Sweden.

                Return:
                - name
                - title
                - company
                - reason
                """;

        return chatClient
                .prompt(prompt)
                .call()
                .entity(new ParameterizedTypeReference<>() {
                });
    }
}
