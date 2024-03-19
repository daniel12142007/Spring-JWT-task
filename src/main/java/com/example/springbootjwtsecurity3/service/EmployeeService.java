package com.example.springbootjwtsecurity3.service;

import com.example.springbootjwtsecurity3.dto.request.EmployeeRequest;
import com.example.springbootjwtsecurity3.dto.response.EmployeeResponse;
import com.example.springbootjwtsecurity3.model.Company;
import com.example.springbootjwtsecurity3.model.Employee;
import com.example.springbootjwtsecurity3.model.User;
import com.example.springbootjwtsecurity3.model.enums.Role;
import com.example.springbootjwtsecurity3.repository.CompanyRepository;
import com.example.springbootjwtsecurity3.repository.EmployeeRepository;
import com.example.springbootjwtsecurity3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public EmployeeResponse saveEmployee(EmployeeRequest request, Long companyId) {
        if (employeeRepository.existsByEmail(request.getEmail()) && userRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("Этот email уже сущесвтует");
        Employee employee = new Employee();
        Company company = companyRepository.findById(companyId).orElseThrow(RuntimeException::new);
        employee.setCompany(company);
        employee.setSalary(request.getSalary());
        employee.setFirstName(request.getFirstName());
        employee.setEmail(request.getEmail());
        employee.setLastName(request.getLastName());
        return map(employeeRepository.save(employee));
    }

    public EmployeeResponse findById(Long id) {
        return map(employeeRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    public List<EmployeeResponse> findAll() {
        return employeeRepository.findAllResponse();
    }

    public String deleteById(Long id) {
        employeeRepository.deleteById(id);
        return "Deleted!";
    }

    private EmployeeResponse map(Employee employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .email(employee.getEmail())
                .salary(employee.getSalary())
                .lastName(employee.getLastName())
                .firstName(employee.getFirstName())
                .build();
    }
}