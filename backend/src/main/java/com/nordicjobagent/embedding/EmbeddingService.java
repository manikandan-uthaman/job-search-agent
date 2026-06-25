package com.nordicjobagent.embedding;

import com.nordicjobagent.agent.discovery.DiscoveryContext;
import com.nordicjobagent.company.domain.Company;
import com.nordicjobagent.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmbeddingService {

    private final CompanyRepository companyRepository;

    private final VectorStore vectorStore;

    public void generateCompanyEmbedding(UUID companyId) {

        Company company = companyRepository
                .findById(companyId)
                .orElseThrow();

        String content = """
                Company: %s
                Country: %s
                Hiring: %s
                Score: %s
                """
                .formatted(
                        company.getName(),
                        company.getCountry(),
                        company.getHiring(),
                        company.getScore()
                );

        Map<String, Object> metadata = Map.of(
                "companyId", company.getId().toString(),
                "companyName", company.getName(),
                "country", company.getCountry(),
                "hiring", company.getHiring(),
                "score", company.getScore()
        );

        Document document = new Document(
                content,
                metadata
        );

        vectorStore.add(List.of(document));
    }

    public List<SemanticSearchResponse> search(
            String query) {

        return vectorStore
                .similaritySearch(
                        SearchRequest.builder()
                                .query(query)
                                .topK(5)
                                .build()
                )
                .stream()
                .map(doc -> new SemanticSearchResponse(
                        doc.getText(),
                        doc.getScore()
                ))
                .toList();
    }

    public List<Document> searchDocuments(
            String query) {

        return vectorStore.similaritySearch(
                SearchRequest.builder()
                        .query(query)
                        .topK(5)
                        .build()
        );
    }

    public List<DiscoveryContext> findRelevantCompanies(
            String query) {

        List<Document> documents =
                searchDocuments(query);

        return documents.stream()
                .map(this::toDiscoveryContext)
                .toList();
    }

    private DiscoveryContext toDiscoveryContext(
            Document document) {

        Map<String, Object> metadata =
                document.getMetadata();

        return new DiscoveryContext(

                metadata.getOrDefault(
                        "companyId",
                        ""
                ).toString(),

                metadata.getOrDefault(
                        "companyName",
                        "Unknown"
                ).toString(),

                metadata.getOrDefault(
                        "country",
                        "Unknown"
                ).toString(),

                document.getText()
        );
    }
}