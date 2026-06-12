package com.nordicjobagent.embedding;

import com.nordicjobagent.company.domain.Company;
import com.nordicjobagent.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
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

        Document document = new Document(
                content
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
}