package com.example.springbootjwtsecurity3.service;

import com.example.springbootjwtsecurity3.dto.response.CompanyResponse;
import com.example.springbootjwtsecurity3.model.Company;
import com.example.springbootjwtsecurity3.model.Director;
import com.example.springbootjwtsecurity3.repository.CompanyRepository;
import com.example.springbootjwtsecurity3.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final DirectorRepository directorRepository;

    public CompanyResponse save(String name, String phone, String address, Long directorId) {
        Director director = directorRepository.findById(directorId).orElseThrow();
        Company company = new Company();
        company.setName(name);
        company.setAddress(address);
        company.setPhone(phone);
        company.setDirector(director);
        return map(companyRepository.save(company));
    }

    public CompanyResponse findById(Long id) {
        return map(companyRepository.findById(id).orElseThrow());
    }

    public List<CompanyResponse> findAll() {
        return companyRepository.findAllResponse();
    }

    public String deleteById(Long id) {
        companyRepository.deleteById(id);
        return "deleted";
    }

    private CompanyResponse map(Company company) {
        return CompanyResponse.builder()
                .id(company.getId())
                .name(company.getName())
                .phone(company.getPhone())
                .address(company.getAddress())
                .directorName(company.getDirector().getFirstName())
                .build();
    }
}