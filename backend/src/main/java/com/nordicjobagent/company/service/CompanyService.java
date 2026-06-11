package com.nordicjobagent.company.service;

import com.nordicjobagent.company.domain.Company;
import com.nordicjobagent.company.dto.CreateCompanyRequest;
import com.nordicjobagent.company.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CompanyService {

    public final CompanyRepository companyRepository;

    public Company createCompany(CreateCompanyRequest request) {
        return companyRepository.save(mapToCompanyDomainModel(request));
    }
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
    public Company getCompanyById(String id) {
        return companyRepository.findById(UUID.fromString(id)).orElse(null);
    }

    private Company mapToCompanyDomainModel(CreateCompanyRequest request) {
        Company c = new Company();
        c.setName(request.name());
        c.setWebsite(request.website());
        c.setHeadquarters(request.headquarters());
        c.setCountry(request.country());
        c.setHiring(request.hiring());
        c.setScore(request.score());
        c.setLastResearchedAt(Instant.now());
        return c;
    }
}
