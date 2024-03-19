package com.example.springbootjwtsecurity3.api;

import com.example.springbootjwtsecurity3.dto.response.CompanyResponse;
import com.example.springbootjwtsecurity3.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
@RequestMapping("api/v1/company")
public class CompanyApi {
    private final CompanyService companyService;

    @PostMapping("save")
    public CompanyResponse save(@RequestParam String companyName,
                                @RequestParam String phone,
                                @RequestParam String address,
                                @RequestParam Long directorId) {
        return companyService.save(companyName, phone, address, directorId);
    }

    @GetMapping("find/by/{id}")
    public CompanyResponse findById(@PathVariable Long id) {
        return companyService.findById(id);
    }

    @GetMapping("find/all")
    public List<CompanyResponse> findAll() {
        return companyService.findAll();
    }

    @DeleteMapping("delete/by/{id}")
    public String deleteById(@PathVariable Long id) {
        return companyService.deleteById(id);
    }
}