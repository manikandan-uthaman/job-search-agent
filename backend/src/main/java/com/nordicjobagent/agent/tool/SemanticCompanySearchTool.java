package com.nordicjobagent.agent.tool;

import com.nordicjobagent.embedding.EmbeddingService;
import com.nordicjobagent.embedding.SemanticSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SemanticCompanySearchTool {

    private final EmbeddingService embeddingService;

    @Tool(description =
            "Find companies matching skills and experience")
    public List<SemanticSearchResponse> searchCompanies(
            String profileDescription) {

        return embeddingService.search(profileDescription);
    }
}
