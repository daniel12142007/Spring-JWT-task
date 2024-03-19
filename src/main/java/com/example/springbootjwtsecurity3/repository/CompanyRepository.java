package com.example.springbootjwtsecurity3.repository;

import com.example.springbootjwtsecurity3.dto.response.CompanyResponse;
import com.example.springbootjwtsecurity3.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("""
            select new com.example.springbootjwtsecurity3.dto.response.CompanyResponse(
            company.id,
            company.name,
            company.phone,
            company.address,
            company.director.firstName
            )
            from Company company
            """)
    List<CompanyResponse> findAllResponse();
}