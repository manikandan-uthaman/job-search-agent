package com.nordicjobagent.company.controller;

import com.nordicjobagent.company.domain.Company;
import com.nordicjobagent.company.dto.CreateCompanyRequest;
import com.nordicjobagent.company.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@AllArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public Company createCompany(@RequestBody CreateCompanyRequest request) {
        return companyService.createCompany(request);
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable String id) {
        return companyService.getCompanyById(id);
    }
}
