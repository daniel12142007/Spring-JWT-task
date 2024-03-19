package com.example.springbootjwtsecurity3.repository;

import com.example.springbootjwtsecurity3.dto.response.EmployeeResponse;
import com.example.springbootjwtsecurity3.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmail(String email);

    @Query("""
            select new com.example.springbootjwtsecurity3.dto.response.EmployeeResponse(
            employe.id,
            employe.firstName,
            employe.lastName,
            employe.email,
            employe.salary
            )
            from Employee employe
            order by employe.id
            """)
    List<EmployeeResponse> findAllResponse();
}