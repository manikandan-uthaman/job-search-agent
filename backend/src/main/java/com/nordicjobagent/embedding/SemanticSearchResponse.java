package com.nordicjobagent.embedding;

public record SemanticSearchResponse(
        String content,
        Double score
) {
}
