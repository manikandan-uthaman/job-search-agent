package com.nordicjobagent.agent.tool;

import com.nordicjobagent.company.domain.Company;
import com.nordicjobagent.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CompanySearchTool {

    private final CompanyRepository companyRepository;

    @Tool(description =
            "Find companies in a specific country")
    public List<CompanyToolResponse> findCompaniesByCountry(
            String country) {

        return companyRepository
                .findByCountryIgnoreCase(country)
                .stream()
                .map(company -> new CompanyToolResponse(
                        company.getName(),
                        company.getCountry(),
                        company.getHiring(),
                        company.getScore()
                ))
                .toList();
    }
}
