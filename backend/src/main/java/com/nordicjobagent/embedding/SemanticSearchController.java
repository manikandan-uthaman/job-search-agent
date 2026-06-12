package com.nordicjobagent.embedding;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/embeddings")
@RequiredArgsConstructor
public class SemanticSearchController {

    private final EmbeddingService embeddingService;

    @PostMapping("/company/{companyId}")
    public void generateEmbedding(
            @PathVariable UUID companyId) {

        embeddingService
                .generateCompanyEmbedding(
                        companyId
                );
    }

    @GetMapping("/search")
    public List<SemanticSearchResponse> search(
            @RequestParam String q) {

        return embeddingService.search(q);
    }
}